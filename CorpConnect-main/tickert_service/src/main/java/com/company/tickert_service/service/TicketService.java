package com.company.tickert_service.service;

import com.company.tickert_service.dto.*;

import java.util.List;

public interface TicketService {

    TicketResponse createTicket(CreateTicketRequest req, String requesterId);

    List<TicketResponse> getMyTickets(String userId);

    List<TicketResponse> getDeptTickets(String departmentId);

    List<TicketResponse> getAssignedTickets(String assignedTo);

    TicketResponse getTicket(String ticketId);

    void assignTicket(String ticketId, String assignedTo, String performedBy);

    void addComment(String ticketId, CommentRequest req, String senderId);

    void closeTicket(String ticketId, String performedBy);
}