package com.company.hiring_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPost {

    @Id
    private String id;

    private String jobNumber;

    private String title;
    private String description;
    private String department;
    private String location;

    private String status; // OPEN, CLOSED, HOLD

    private String createdBy;

    private LocalDateTime createdAt;

    // Optional link with ticket-service
    private String ticketId;

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	
    
}