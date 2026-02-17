package com.company.hiring_service.dto;


import jakarta.validation.constraints.NotBlank;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyJobRequest {
    private String jobPostId;
    private String candidateId;
    private String candidateName;
    private String candidateEmail;
    private String skills;
    private Integer experienceYears;
    private String location;
    private String resumeObjectKey;
}
