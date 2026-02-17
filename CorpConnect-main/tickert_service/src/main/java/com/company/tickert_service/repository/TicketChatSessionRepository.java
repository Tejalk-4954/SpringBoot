package com.company.tickert_service.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.company.tickert_service.entity.TicketChatSession;
public interface TicketChatSessionRepository extends JpaRepository<TicketChatSession, String> {
    TicketChatSession findBySessionId(String sessionId);
}
