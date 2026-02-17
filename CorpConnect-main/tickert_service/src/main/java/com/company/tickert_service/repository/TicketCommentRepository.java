package com.company.tickert_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.tickert_service.entity.TicketComment;
public interface TicketCommentRepository extends JpaRepository<TicketComment, String> {
    List<TicketComment> findByTicketIdOrderByCreatedAtAsc(String ticketId);
}

