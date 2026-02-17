package com.company.hiring_service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaConsumerService {

    @KafkaListener(topics = "hiring-job-events", groupId = "hiring-service-group")
    public void listen(ConsumerRecord<String, String> record) {
        String eventJson = record.value();
        // Deserialize and process the event JSON here
        System.out.println("Received event: " + eventJson);
        // Implement event handling logic
    }
}