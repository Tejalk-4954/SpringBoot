package com.company.hiring_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDTO {
    private String id;
    private String jobId;
    private String candidateName;
    private String candidateEmail;
    private String resumeFileId;   // resume object key or file id
    private String status;         // APPLIED, SHORTLISTED, REJECTED, INTERVIEW_SCHEDULED, SELECTED

    private String skills;
    private Integer experienceYears;
    private String location;

    private LocalDateTime appliedAt;

	
    
    
}