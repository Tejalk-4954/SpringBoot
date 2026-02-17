package com.company.hiring_service.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "resume_file")
public class ResumeFile {
    @Id
    @Column(length=36)
    private String id;

    @Column(name="application_id", length=36)
    private String applicationId;

    @Column(name="object_key", length=512, nullable=false)
    private String objectKey;

    @Column(name="file_name")
    private String fileName;

    @Column(name="mime_type")
    private String mimeType;

    @Column(name="size_bytes")
    private Long sizeBytes;

    @Column(name="uploaded_by", length=36)
    private String uploadedBy;

    @Column(name="created_at")
    private Instant createdAt;

    @PrePersist
    public void prePersist(){
        if (this.id == null) this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
    }
    // getters/setters...
}
