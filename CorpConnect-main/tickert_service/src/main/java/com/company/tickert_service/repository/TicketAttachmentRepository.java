package com.company.tickert_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.tickert_service.entity.TicketAttachment;
public interface TicketAttachmentRepository extends JpaRepository<TicketAttachment, String> {
    List<TicketAttachment> findByTicketId(String ticketId);
}
