package com.company.tickert_service.controller;

import com.company.tickert_service.dto.ChatMessagePayload;
import com.company.tickert_service.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatWebSocketController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    // Client sends to /app/chat.send.{ticketId}
    @MessageMapping("/chat.send.{ticketId}")
    public void receiveAndBroadcast(@DestinationVariable String ticketId,
                                    @Payload ChatMessagePayload payload,
                                    Principal principal) {

        // ensure ticketId consistency
        payload.setTicketId(ticketId);

        // set sender based on principal if available (handshake stored subject)
        if (principal != null && (payload.getSenderId() == null || payload.getSenderId().isBlank())) {
            payload.setSenderId(principal.getName());
        }

        // If no senderId still present, reject (or you can allow anonymous)
        if (payload.getSenderId() == null || payload.getSenderId().isBlank()) {
            // do not persist anonymous messages — optionally send error to client
            return;
        }

        // persist + publish to kafka (and returns saved DTO)
        ChatMessagePayload saved = chatService.saveAndPublish(payload);

        // Broadcast to subscribers of /topic/tickets/{ticketId}
        messagingTemplate.convertAndSend("/topic/tickets/" + ticketId, saved);
    }
}
