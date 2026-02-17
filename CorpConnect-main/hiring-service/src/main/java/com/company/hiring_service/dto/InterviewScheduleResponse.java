package com.company.hiring_service.dto;



import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewScheduleResponse {
    private String scheduleId;
    private String applicationId;
    private String interviewSlotId;
    private String scheduledBy;
    private Instant scheduledAt;
    private String status;
}
