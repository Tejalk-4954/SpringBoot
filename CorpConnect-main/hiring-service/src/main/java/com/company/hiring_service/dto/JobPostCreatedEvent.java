package com.company.hiring_service.dto;

import lombok.Data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostCreatedEvent {
    private String jobPostId;
    private String title;
    private String department;
	
    
    
}
