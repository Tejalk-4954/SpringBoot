package com.company.user_service.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_outbox")
public class OutboxEvent {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name="event_type", length = 100)
    private String eventType;

    @Lob
    @Column(name="payload", columnDefinition = "TEXT")
    private String payload; // JSON string

    @Column(name="aggregate_id", length = 36)
    private String aggregateId;

    @Column(name="status", length = 20)
    private String status = "PENDING"; // PENDING, PUBLISHED, FAILED

    @Column(name="publish_attempts")
    private int publishAttempts = 0;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="published_at")
    private Instant publishedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID().toString();
        createdAt = Instant.now();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(String aggregateId) {
		this.aggregateId = aggregateId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPublishAttempts() {
		return publishAttempts;
	}

	public void setPublishAttempts(int publishAttempts) {
		this.publishAttempts = publishAttempts;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Instant publishedAt) {
		this.publishedAt = publishedAt;
	}

    // getters/setters
    
    
}