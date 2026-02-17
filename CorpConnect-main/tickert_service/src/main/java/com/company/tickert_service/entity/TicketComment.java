package com.company.tickert_service.entity;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ticket_comments")
@Data
public class TicketComment {
    @Id
    @Column(length=36)
    private String id;

    @Column(name="ticket_id", length=36, nullable=false)
    private String ticketId;

    @Column(name="sender_id", length=36)
    private String senderId;

    @Column(columnDefinition="TEXT")
    private String message;

    @Column(name="is_internal")
    private Boolean internal = Boolean.FALSE;

    @Column(columnDefinition = "JSON")
    private String attachments; // store JSON array of object keys / metadata as string

    @Column(name="created_at")
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
    }

	public TicketComment() {
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

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getInternal() {
		return internal;
	}

	public void setInternal(Boolean internal) {
		this.internal = internal;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
