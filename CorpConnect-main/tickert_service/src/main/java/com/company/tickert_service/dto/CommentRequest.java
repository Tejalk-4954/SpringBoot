package com.company.tickert_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CommentRequest {
    @NotBlank
    private String message;
    private Boolean internal = false;
    private String attachmentsJson; // optional JSON array string
	public CommentRequest() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getInternal() {
		return internal;
	}
	public void setInternal(Boolean internal) {
		this.internal = internal;
	}
	public String getAttachmentsJson() {
		return attachmentsJson;
	}
	public void setAttachmentsJson(String attachmentsJson) {
		this.attachmentsJson = attachmentsJson;
	}
    
    
}
