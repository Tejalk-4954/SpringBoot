package com.company.user_service.outbox;

import java.time.Instant;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.company.user_service.entity.OutboxEvent;
import com.company.user_service.repo.OutboxRepository;

import jakarta.transaction.Transactional;

@Component
public class OutboxPublisher {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    // mapping eventType -> topic
    // You can put this mapping in a config file or use simple rule: user.created -> user.created
    private final String topicPrefix = "user.";

    public OutboxPublisher(OutboxRepository outboxRepository,
                           KafkaTemplate<String, String> kafkaTemplate) {
        this.outboxRepository = outboxRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelayString = "${outbox.publish.interval-ms:5000}")
    @Transactional
    public void publishPending() {
        List<OutboxEvent> pending = outboxRepository.findByStatusOrderByCreatedAtAsc("PENDING");
        for (OutboxEvent e : pending) {
            try {
                String topic = mapEventToTopic(e.getEventType());
                kafkaTemplate.send(topic, e.getAggregateId(), e.getPayload()).get();
                outboxRepository.markPublished(e.getId(), "PUBLISHED", Instant.now());

            } catch (Exception ex) {
                // increment attempts and leave it PENDING or set FAILED after threshold
                e.setPublishAttempts(e.getPublishAttempts() + 1);
                outboxRepository.save(e);
            }
        }
    }

    private String mapEventToTopic(String eventType) {
        // e.g., USER_CREATED -> user.created
        return topicPrefix + eventType.toLowerCase().replace('_', '.');
    }
}
