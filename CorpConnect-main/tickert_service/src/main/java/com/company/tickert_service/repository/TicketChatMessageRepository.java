package com.company.tickert_service.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.company.tickert_service.entity.TicketChatMessage;

public interface TicketChatMessageRepository extends JpaRepository<TicketChatMessage, String> {
    List<TicketChatMessage> findByTicketIdOrderByCreatedAtAsc(String ticketId);
}
