package com.company.tickert_service.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CreateTicketRequest {
    @NotBlank
    private String title;
    private String description;
    @NotBlank
    private String departmentId;
    @NotBlank
    private String priority; // LOW, MEDIUM, HIGH, URGENT
	public CreateTicketRequest() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
    
    
}
