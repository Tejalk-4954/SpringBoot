package com.company.tickert_service.entity;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="ticket_events")
@Data
public class TicketEvent {
    @Id
    @Column(length=36)
    private String id;

    @Column(name="ticket_id", length=36)
    private String ticketId;

    @Column(name="event_type", length=50)
    private String eventType;

    @Column(columnDefinition="JSON")
    private String payload;

    @Column(name="performed_by", length=36)
    private String performedBy;

    @Column(name="created_at")
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
    }

	public TicketEvent() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
