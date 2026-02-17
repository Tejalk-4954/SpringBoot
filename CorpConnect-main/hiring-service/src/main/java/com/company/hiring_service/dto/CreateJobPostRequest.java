package com.company.hiring_service.dto;


import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateJobPostRequest {
    private String title;
    private String description;
    private String requiredSkills;
    private Integer experienceYears;
    private String location;
}
