package com.company.hiring_service.repository;

import com.company.hiring_service.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, String> {

    List<JobPost> findByStatus(String status);

    List<JobPost> findByCreatedBy(String createdBy);
}