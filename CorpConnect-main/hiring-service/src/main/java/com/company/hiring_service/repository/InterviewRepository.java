package com.company.hiring_service.repository;

import com.company.hiring_service.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, String> {

    List<Interview> findByInterviewerId(String interviewerId);
}