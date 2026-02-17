package com.company.hiring_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "interviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private JobApplication application;

    private LocalDateTime interviewDate;

    private String interviewerId;

    private String mode;  // ONLINE, OFFLINE

    private String status; // SCHEDULED, SELECTED, REJECTED

    private String notes;

    private String meetLink;

    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime decidedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JobApplication getApplication() {
		return application;
	}

	public void setApplication(JobApplication application) {
		this.application = application;
	}

	public LocalDateTime getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDateTime interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(String interviewerId) {
		this.interviewerId = interviewerId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getMeetLink() {
		return meetLink;
	}

	public void setMeetLink(String meetLink) {
		this.meetLink = meetLink;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getDecidedAt() {
		return decidedAt;
	}

	public void setDecidedAt(LocalDateTime decidedAt) {
		this.decidedAt = decidedAt;
	}

	
	
    
    
}