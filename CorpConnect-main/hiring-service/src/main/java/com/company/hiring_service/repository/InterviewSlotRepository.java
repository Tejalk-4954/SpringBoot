package com.company.hiring_service.repository;

import com.company.hiring_service.entity.InterviewSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewSlotRepository extends JpaRepository<InterviewSlot, String> {}