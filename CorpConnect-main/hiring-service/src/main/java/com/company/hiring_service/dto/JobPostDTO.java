package com.company.hiring_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostDTO {
    private String id;
    private String jobNumber;
    private String title;
    private String description;
    private String department;
    private String location;
    private String status;
    private String createdBy;
    private String ticketId;
	
    
    
}