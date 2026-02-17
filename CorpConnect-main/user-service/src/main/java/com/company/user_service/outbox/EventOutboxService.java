package com.company.user_service.outbox;

import org.springframework.stereotype.Service;

import com.company.user_service.entity.OutboxEvent;
import com.company.user_service.repo.OutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class EventOutboxService {

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public EventOutboxService(OutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void enqueue(String eventType, Object payload, String aggregateId) {
        try {
            OutboxEvent event = new OutboxEvent();
            event.setEventType(eventType);
            event.setPayload(objectMapper.writeValueAsString(payload));
            event.setAggregateId(aggregateId);
            outboxRepository.save(event);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to enqueue outbox event", ex);
        }
    }
}
