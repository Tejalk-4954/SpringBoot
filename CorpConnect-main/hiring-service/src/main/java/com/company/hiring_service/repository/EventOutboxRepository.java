package com.company.hiring_service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.hiring_service.entity.EventOutbox;

@Repository
public interface EventOutboxRepository extends JpaRepository<EventOutbox, String> {
    List<EventOutbox> findByStatus(String status);
}
