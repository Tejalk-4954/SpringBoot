package com.company.tickert_service.service;

import com.company.tickert_service.dto.ChatMessagePayload;
import com.company.tickert_service.entity.TicketChatMessage;
import com.company.tickert_service.repository.TicketChatMessageRepository;
import com.company.tickert_service.util.MinioUtil;
import com.company.tickert_service.util.TicketEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final TicketChatMessageRepository chatRepo;
    private final MinioUtil minioUtil;
    private final ObjectMapper mapper;
    private final TicketEventPublisher publisher;

    public ChatServiceImpl(TicketChatMessageRepository chatRepo,
                           MinioUtil minioUtil,
                           ObjectMapper mapper,
                           TicketEventPublisher publisher) {
        this.chatRepo = chatRepo;
        this.minioUtil = minioUtil;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    @Transactional
    public ChatMessagePayload saveAndPublish(ChatMessagePayload payload) {
        try {
            // Validate attachments: keep only those that exist
            List<String> validAttachments = null;
            if (payload.getAttachments() != null && !payload.getAttachments().isEmpty()) {
                validAttachments = new ArrayList<>();
                for (String key : payload.getAttachments()) {
                    if (key != null && !key.isBlank() && minioUtil.objectExists(key)) {
                        validAttachments.add(key);
                    }
                }
                // if none valid, set to null to avoid storing empty array
                if (validAttachments.isEmpty()) validAttachments = null;
            }

            TicketChatMessage e = new TicketChatMessage();
            e.setTicketId(payload.getTicketId());
            e.setSenderId(payload.getSenderId());
            e.setMessage(payload.getMessage());
            e.setInternal(payload.isInternal());
            if (validAttachments != null) {
                e.setAttachments(mapper.writeValueAsString(validAttachments));
            }
            TicketChatMessage saved = chatRepo.save(e);

            ChatMessagePayload out = new ChatMessagePayload();
            out.setId(saved.getId());
            out.setTicketId(saved.getTicketId());
            out.setSenderId(saved.getSenderId());
            out.setMessage(saved.getMessage());
            out.setInternal(Boolean.TRUE.equals(saved.getInternal()));
            if (saved.getAttachments() != null) {
                List<String> atts = mapper.readValue(saved.getAttachments(), List.class);
                out.setAttachments(atts);
            }
            out.setCreatedAt(saved.getCreatedAt());

            // publish Kafka event as JSON
            String json = mapper.writeValueAsString(out);
            publisher.publish("ticket.chat", saved.getTicketId(), json);

            return out;
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save/publish chat message", ex);
        }
    }

    @Override
    public List<ChatMessagePayload> getMessages(String ticketId) {
        return chatRepo.findByTicketIdOrderByCreatedAtAsc(ticketId).stream().map(m -> {
            ChatMessagePayload p = new ChatMessagePayload();
            p.setId(m.getId());
            p.setTicketId(m.getTicketId());
            p.setSenderId(m.getSenderId());
            p.setMessage(m.getMessage());
            p.setInternal(Boolean.TRUE.equals(m.getInternal()));
            try {
                if (m.getAttachments() != null) {
                    p.setAttachments(mapper.readValue(m.getAttachments(), List.class));
                }
            } catch (Exception ignore) {}
            p.setCreatedAt(m.getCreatedAt());
            return p;
        }).collect(Collectors.toList());
    }

    @Override
    public String generatePresignedPut(String ticketId, String filename, String contentType, long size, int expirySeconds) {
        String objectKey = "tickets/" + ticketId + "/" + System.currentTimeMillis() + "-" + filename.replaceAll("[^a-zA-Z0-9._-]", "_");
        String presigned = minioUtil.presignPut(objectKey, expirySeconds);
        return presigned + "||" + objectKey;
    }

    @Override
    public String generatePresignedGet(String objectKey, int expirySeconds) {
        return minioUtil.presignGet(objectKey, expirySeconds);
    }

    @Override
    public boolean checkObjectExists(String objectKey) {
        return minioUtil.objectExists(objectKey);
    }
}
