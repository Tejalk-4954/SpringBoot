package com.company.tickert_service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.tickert_service.entity.TicketEvent;
public interface TicketEventRepository extends JpaRepository<TicketEvent, String> {
    List<TicketEvent> findByTicketIdOrderByCreatedAtAsc(String ticketId);
}
