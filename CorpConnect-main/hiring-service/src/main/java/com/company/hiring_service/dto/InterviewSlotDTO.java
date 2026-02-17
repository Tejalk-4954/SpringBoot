package com.company.hiring_service.dto;

import lombok.Builder;
import lombok.Data;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewSlotDTO {
    private String id;
    private String managerId;
    private LocalDate slotDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isBooked;
}
