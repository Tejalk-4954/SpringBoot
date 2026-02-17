package com.company.hiring_service.entity;



import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event_outbox")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventOutbox {
    @Id
    @Column(length = 36, updatable = false, nullable = false)
    private String id;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    @Column(name = "event_type")
    private String eventType;

    @Column(columnDefinition = "json")
    private String payload;

    private String status; // PENDING, PUBLISHED, FAILED

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
