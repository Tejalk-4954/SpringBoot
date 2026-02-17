package com.pgfinder.pgfinder_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgfinder.pgfinder_backend.Entity.ChatMessage;
import com.pgfinder.pgfinder_backend.service.ChatService;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @MessageMapping("/sendMessage")
    public void sendMessage(ChatMessage message) {
        chatService.sendMessage(message);
    }
}