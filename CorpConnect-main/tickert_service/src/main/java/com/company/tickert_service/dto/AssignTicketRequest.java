package com.company.tickert_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class AssignTicketRequest {
    @NotBlank
    private String assignedTo; // user id

	public AssignTicketRequest() {
		super();
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
    
	
    
}
