package com.company.hiring_service.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "candidate")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {

    @Id
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Column(nullable = false)
    private String password; // hashed

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "phone_verified")
    private boolean phoneVerified = false;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();
}