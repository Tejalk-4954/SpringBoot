package com.company.hiring_service.repository;

import com.company.hiring_service.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, String> {

    List<JobApplication> findByJobPostId(String jobPostId);

    List<JobApplication> findByCreatedBy(String createdBy);
}