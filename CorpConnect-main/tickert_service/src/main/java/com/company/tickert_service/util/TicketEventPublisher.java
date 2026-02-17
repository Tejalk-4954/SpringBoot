package com.company.tickert_service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TicketEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(TicketEventPublisher.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TicketEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String topic, String key, String payload) {
        kafkaTemplate.send(topic, key, payload)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to send Kafka message to topic [{}] with key [{}]", topic, key, ex);
                    } else {
                        log.info("Kafka message sent → topic [{}], key [{}]", topic, key);
                    }
                });
    }
}
