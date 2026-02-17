package com.company.tickert_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "ticket.created", groupId = "ticket-group")
    public void onTicketCreated(String message) {
        System.out.println("🔥 Kafka Received (Created): " + message);
    }

    @KafkaListener(topics = "ticket.assigned", groupId = "ticket-group")
    public void onAssigned(String message) {
        System.out.println("🔥 Kafka Received (Assigned): " + message);
    }

    @KafkaListener(topics = "ticket.commented", groupId = "ticket-group")
    public void onComment(String message) {
        System.out.println("🔥 Kafka Received (Commented): " + message);
    }

    @KafkaListener(topics = "ticket.closed", groupId = "ticket-group")
    public void onClosed(String message) {
        System.out.println("🔥 Kafka Received (Closed): " + message);
    }
}