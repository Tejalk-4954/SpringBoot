package com.company.hiring_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "hiring-job-events";

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String eventJson) {
        kafkaTemplate.send(TOPIC, eventJson);
    }
}