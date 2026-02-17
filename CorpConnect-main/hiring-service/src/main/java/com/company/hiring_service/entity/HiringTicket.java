package com.company.hiring_service.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "hiring_ticket")
public class HiringTicket {
    @Id @Column(length=36) private String id;
    @Column(name="job_post_id", length=36) private String jobPostId;
    @Column(name="created_by", length=36) private String createdBy;
    @Column(name="status") private String status;
    @Column(name="created_at") private Instant createdAt;

    @PrePersist public void prePersist(){ if (this.id==null) this.id = UUID.randomUUID().toString(); this.createdAt = Instant.now(); }
    // getters/setters...
}
