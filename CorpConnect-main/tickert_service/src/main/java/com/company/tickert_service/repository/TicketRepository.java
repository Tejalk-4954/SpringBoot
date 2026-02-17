package com.company.tickert_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.tickert_service.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    Ticket findByTicketNumber(String ticketNumber);
    List<Ticket> findByRaisedBy(String userId);
    List<Ticket> findByAssignedTo(String userId);
    List<Ticket> findByDepartmentId(String departmentId);
    List<Ticket> findByStatus(String status);
}
