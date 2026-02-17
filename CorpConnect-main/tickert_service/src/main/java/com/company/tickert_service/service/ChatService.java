package com.company.tickert_service.service;

import com.company.tickert_service.dto.ChatMessagePayload;

import java.util.List;

public interface ChatService {
    ChatMessagePayload saveAndPublish(ChatMessagePayload payload);
    List<ChatMessagePayload> getMessages(String ticketId);
    String generatePresignedPut(String ticketId, String filename, String contentType, long size, int expirySeconds);
    String generatePresignedGet(String objectKey, int expirySeconds);
    boolean checkObjectExists(String objectKey);
}
