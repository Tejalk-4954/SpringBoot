package com.company.tickert_service.entity;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="ticket_chat_sessions")
@Data
public class TicketChatSession {
    @Id
    @Column(length=36)
    private String id;

    @Column(name="ticket_id", length=36, nullable=false)
    private String ticketId;

    @Column(name="session_id", length=128, unique=true, nullable=false)
    private String sessionId;

    @Column(name="started_at")
    private Instant startedAt;

    @Column(name="ended_at")
    private Instant endedAt;

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID().toString();
        this.startedAt = Instant.now();
    }

	public TicketChatSession() {
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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Instant getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Instant startedAt) {
		this.startedAt = startedAt;
	}

	public Instant getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(Instant endedAt) {
		this.endedAt = endedAt;
	}
    
    
}
