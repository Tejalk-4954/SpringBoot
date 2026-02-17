package com.company.hiring_service.dto;

import lombok.Data;

@Data
public class PresignResponse {
    private String objectKey;
    private String presignedUrl;
    private String downloadUrl;
	
    
}