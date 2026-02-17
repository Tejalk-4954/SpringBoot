package com.company.user_service.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="file_metadata")
public class FileMetadata {
    @Id
    @Column(length = 36)
    private String id;

    @Column(name="file_name")
    private String fileName;

    private String bucket;

    @Column(name="object_key")
    private String objectKey;

    @Column(name="mime_type")
    private String mimeType;

    private Long size;

    @Column(name="uploaded_at")
    private Instant uploadedAt;

    @Column(name="uploaded_by", length = 36)
    private String uploadedBy;

    @PrePersist
    public void prePersist() {
        if (this.id == null) this.id = UUID.randomUUID().toString();
        this.uploadedAt = Instant.now();
    }
    // getters/setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Instant getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(Instant uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
    
    
}
