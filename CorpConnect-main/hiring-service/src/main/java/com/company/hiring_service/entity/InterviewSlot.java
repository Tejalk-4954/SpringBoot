package com.company.hiring_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "interview_slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewSlot {

    @Id
    private String id;

    private String managerId;

    private LocalDate slotDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private Boolean isBooked;
}