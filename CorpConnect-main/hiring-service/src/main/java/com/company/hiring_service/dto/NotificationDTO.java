package com.company.hiring_service.dto;

import lombok.Data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private String id;
    private String userId;
    private String title;
    private String message;
    private String type;  // TICKET_ASSIGNED, INTERVIEW_SCHEDULED, etc.
    private Boolean isRead;
    private LocalDateTime createdAt;
}
