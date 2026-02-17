package com.company.tickert_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTicketEvent(Object event) {
        kafkaTemplate.send("ticket-events", event);
        System.out.println("✅ Kafka Event Sent: " + event);
    }
}