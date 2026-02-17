package com.company.hiring_service.entity;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @Column(length = 36, updatable = false, nullable = false)
    private String id;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    @Column(name = "user_id", length = 36)
    private String userId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String type; // TICKET_ASSIGNED, INTERVIEW_SCHEDULED, etc.

    @Column(name = "is_read")
    private Boolean isRead = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
