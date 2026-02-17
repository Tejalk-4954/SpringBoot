package com.company.hiring_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.hiring_service.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, String> {
    Optional<Candidate> findByEmail(String email);
}
