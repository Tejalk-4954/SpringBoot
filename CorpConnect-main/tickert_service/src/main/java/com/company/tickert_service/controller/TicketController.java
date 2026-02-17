package com.company.tickert_service.controller;

import com.company.tickert_service.dto.*;
import com.company.tickert_service.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // --------- Create ticket ---------
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody CreateTicketRequest req,
                                                       Principal principal) {
        // In your system: JWT subject = user email
        String requesterId = principal.getName();
        TicketResponse resp = ticketService.createTicket(req, requesterId);
        return ResponseEntity.ok(resp);
    }

    // --------- My tickets (raised by me) ---------
    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<TicketResponse>> myTickets(Principal principal) {
        String userId = principal.getName();
        List<TicketResponse> list = ticketService.getMyTickets(userId);
        return ResponseEntity.ok(list);
    }

    // --------- Tickets assigned to me ---------
    @GetMapping("/assigned")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<TicketResponse>> myAssignedTickets(Principal principal) {
        String userId = principal.getName();
        List<TicketResponse> list = ticketService.getAssignedTickets(userId);
        return ResponseEntity.ok(list);
    }

    // --------- Get single ticket (any authenticated; you can later enforce finer rules in service) ---------
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable String id) {
        TicketResponse resp = ticketService.getTicket(id);
        return ResponseEntity.ok(resp);
    }

    // --------- Tickets by department (admin / heads / managers) ---------
    @GetMapping("/dept")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','HR_HEAD','ITS_HEAD')")
    public ResponseEntity<List<TicketResponse>> deptTickets(@RequestParam String departmentId) {
        List<TicketResponse> list = ticketService.getDeptTickets(departmentId);
        return ResponseEntity.ok(list);
    }

    // --------- Assign ticket (admin / heads / managers) ---------
    @PostMapping("/{id}/assign")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','HR_HEAD','ITS_HEAD')")
    public ResponseEntity<Void> assign(@PathVariable String id,
                                       @RequestBody AssignTicketRequest req,
                                       Principal principal) {
        String performedBy = principal.getName();
        ticketService.assignTicket(id, req.getAssignedTo(), performedBy);
        return ResponseEntity.noContent().build();
    }

    // --------- Comment on ticket (any authenticated) ---------
    @PostMapping("/{id}/comment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> comment(@PathVariable String id,
                                        @RequestBody CommentRequest req,
                                        Principal principal) {
        String senderId = principal.getName();
        ticketService.addComment(id, req, senderId);
        return ResponseEntity.noContent().build();
    }

    // --------- Close ticket (admin / heads / managers) ---------
    @PostMapping("/{id}/close")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','HR_HEAD','ITS_HEAD')")
    public ResponseEntity<Void> close(@PathVariable String id,
                                      Principal principal) {
        String performedBy = principal.getName();
        ticketService.closeTicket(id, performedBy);
        return ResponseEntity.noContent().build();
    }
}