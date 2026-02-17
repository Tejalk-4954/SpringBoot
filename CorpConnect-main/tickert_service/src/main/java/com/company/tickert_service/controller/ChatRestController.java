package com.company.tickert_service.controller;

import com.company.tickert_service.dto.ChatMessagePayload;
import com.company.tickert_service.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets/{ticketId}/chat")
public class ChatRestController {

    private final ChatService chatService;

    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Get chat history for a ticket.
     * Frontend can then:
     *  - show message text
     *  - for each attachment objectKey, call /attachments/download-url
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ChatMessagePayload>> history(@PathVariable String ticketId) {
        List<ChatMessagePayload> list = chatService.getMessages(ticketId);
        return ResponseEntity.ok(list);
    }
}