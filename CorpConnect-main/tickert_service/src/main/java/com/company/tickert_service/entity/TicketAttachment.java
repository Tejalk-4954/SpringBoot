package com.company.tickert_service.entity;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="ticket_attachments")
@Data
public class TicketAttachment {
    @Id
    @Column(length=36)
    private String id;

    @Column(name="ticket_id", length=36)
    private String ticketId;

    @Column(name="uploaded_by", length=36)
    private String uploadedBy;

    @Column(name="object_key", length=512)
    private String objectKey;

    @Column(name="file_name", length=255)
    private String fileName;

    @Column(name="mime_type", length=100)
    private String mimeType;

    @Column(name="size_bytes")
    private Long sizeBytes;

    @Column(name="created_at")
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
    }

	public TicketAttachment() {
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

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Long getSizeBytes() {
		return sizeBytes;
	}

	public void setSizeBytes(Long sizeBytes) {
		this.sizeBytes = sizeBytes;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
    
    
    
    
}
