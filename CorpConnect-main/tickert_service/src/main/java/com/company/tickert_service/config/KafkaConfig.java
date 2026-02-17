package com.company.tickert_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic ticketCreatedTopic() {
        return new NewTopic("ticket.created", 1, (short) 1);
    }

    @Bean
    public NewTopic ticketAssignedTopic() {
        return new NewTopic("ticket.assigned", 1, (short) 1);
    }

    @Bean
    public NewTopic ticketCommentedTopic() {
        return new NewTopic("ticket.commented", 1, (short) 1);
    }

    @Bean
    public NewTopic ticketClosedTopic() {
        return new NewTopic("ticket.closed", 1, (short) 1);
    }

    // NEW: chat events
    @Bean
    public NewTopic ticketChatTopic() {
        return new NewTopic("ticket.chat", 1, (short) 1);
    }
}
