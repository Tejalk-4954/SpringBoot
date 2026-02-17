package com.company.tickert_service.service;

import com.company.tickert_service.dto.*;
import com.company.tickert_service.entity.*;
import com.company.tickert_service.repository.*;
import com.company.tickert_service.util.TicketEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepo;
    private final TicketCommentRepository commentRepo;
    private final TicketEventRepository eventRepo;
    private final TicketAttachmentRepository attachRepo;
    private final TicketEventPublisher publisher;
    private final ObjectMapper mapper;

    public TicketServiceImpl(TicketRepository ticketRepo,
                             TicketCommentRepository commentRepo,
                             TicketEventRepository eventRepo,
                             TicketAttachmentRepository attachRepo,
                             TicketEventPublisher publisher,
                             ObjectMapper mapper) {
        this.ticketRepo = ticketRepo;
        this.commentRepo = commentRepo;
        this.eventRepo = eventRepo;
        this.attachRepo = attachRepo;
        this.publisher = publisher;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public TicketResponse createTicket(CreateTicketRequest req, String requesterId) {
        Ticket t = new Ticket();
        t.setTitle(req.getTitle());
        t.setDescription(req.getDescription());
        t.setDepartmentId(req.getDepartmentId());
        t.setPriority(req.getPriority());
        t.setStatus("NEW");
        t.setRaisedBy(requesterId);

        // example: TICK-20251113-1234
        String number = "TICK-" +
                LocalDate.now().toString().replace("-", "") +
                "-" + (System.currentTimeMillis() % 10000);
        t.setTicketNumber(number);

        ticketRepo.save(t);

        publishEvent(t.getId(), "CREATED",
                Map.of("ticketNumber", t.getTicketNumber()),
                requesterId);

        return toDto(t);
    }

    @Override
    public List<TicketResponse> getMyTickets(String userId) {
        return ticketRepo.findByRaisedBy(userId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketResponse> getDeptTickets(String departmentId) {
        return ticketRepo.findByDepartmentId(departmentId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketResponse> getAssignedTickets(String assignedTo) {
        return ticketRepo.findByAssignedTo(assignedTo)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TicketResponse getTicket(String ticketId) {
        Ticket t = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return toDto(t);
    }

    @Override
    @Transactional
    public void assignTicket(String ticketId, String assignedTo, String performedBy) {
        Ticket t = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        t.setAssignedTo(assignedTo);
        t.setStatus("ASSIGNED");
        ticketRepo.save(t);

        publishEvent(ticketId, "ASSIGNED",
                Map.of("assignedTo", assignedTo),
                performedBy);
    }

    @Override
    @Transactional
    public void addComment(String ticketId, CommentRequest req, String senderId) {
        Ticket t = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        TicketComment c = new TicketComment();
        c.setTicketId(ticketId);
        c.setSenderId(senderId);
        c.setMessage(req.getMessage());
        c.setInternal(Boolean.TRUE.equals(req.getInternal()));
        c.setAttachments(req.getAttachmentsJson()); // JSON string of attachment keys
        commentRepo.save(c);

        publishEvent(ticketId, "COMMENTED",
                Map.of("commentId", c.getId()),
                senderId);
    }

    @Override
    @Transactional
    public void closeTicket(String ticketId, String performedBy) {
        Ticket t = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        t.setStatus("CLOSED");
        t.setClosedAt(Instant.now());
        ticketRepo.save(t);

        publishEvent(ticketId, "CLOSED", null, performedBy);
    }

    private TicketResponse toDto(Ticket t) {
        TicketResponse r = new TicketResponse();
        r.setId(t.getId());
        r.setTicketNumber(t.getTicketNumber());
        r.setTitle(t.getTitle());
        r.setDescription(t.getDescription());
        r.setDepartmentId(t.getDepartmentId());
        r.setPriority(t.getPriority());
        r.setStatus(t.getStatus());
        r.setRaisedBy(t.getRaisedBy());
        r.setAssignedTo(t.getAssignedTo());
        r.setCreatedAt(t.getCreatedAt());
        r.setUpdatedAt(t.getUpdatedAt());
        return r;
    }

    private void publishEvent(String ticketId, String eventType, Object payload, String performedBy) {
        try {
            String payloadJson = payload == null ? "{}" : mapper.writeValueAsString(payload);

            TicketEvent e = new TicketEvent();
            e.setTicketId(ticketId);
            e.setEventType(eventType);
            e.setPayload(payloadJson);
            e.setPerformedBy(performedBy);
            eventRepo.save(e);

            String topic = "ticket." + eventType.toLowerCase(); // created/assigned/commented/closed
            publisher.publish(topic, ticketId, payloadJson);
        } catch (Exception ex) {
            // log and move on (no hard fail)
        }
    }
}