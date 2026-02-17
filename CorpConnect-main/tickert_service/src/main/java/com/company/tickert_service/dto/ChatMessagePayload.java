package com.company.tickert_service.dto;

import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
public class ChatMessagePayload {
    private String id;
    private String ticketId;
    private String senderId;
    private String message;
    private boolean internal;
    private List<String> attachments; // list of objectKeys
    private Instant createdAt;
	public ChatMessagePayload() {
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
	public boolean isInternal() {
		return internal;
	}
	public void setInternal(boolean internal) {
		this.internal = internal;
	}
	public List<String> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
