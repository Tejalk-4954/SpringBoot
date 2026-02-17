//package com.company.hiring_service.event;
//
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class HiringEventPublisher {
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    public HiringEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void publish(String topic, String key, String payloadJson) {
//        kafkaTemplate.send(topic, key, payloadJson);
//    }
//}