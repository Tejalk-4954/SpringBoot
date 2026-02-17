package com.pgfinder.pgfinder_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.pgfinder.pgfinder_backend.Entity.ChatMessage;
import com.pgfinder.pgfinder_backend.repository.ChatRepository;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendMessage(ChatMessage message) {
        chatRepository.save(message);
        messagingTemplate.convertAndSendToUser(message.getReceiver(), "/queue/messages", message);
    }
}