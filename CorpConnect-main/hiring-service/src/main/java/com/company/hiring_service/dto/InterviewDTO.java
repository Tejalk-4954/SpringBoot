package com.company.hiring_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDTO {
    private String id;
    private String applicationId;
    private LocalDateTime interviewDate;
    private String interviewerId;
    private String mode;     // ONLINE, OFFLINE
    private String status;   // SCHEDULED, SELECTED, REJECTED, etc.
    private String notes;
    private String meetLink;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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
	
    
}