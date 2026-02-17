package com.company.tickert_service.dto;

import lombok.Data;

@Data
public class PresignResponse {
    private String objectKey;
    private String presignedUrl;
    private String downloadUrl; // optional
	public PresignResponse() {
		super();
	}
	public String getObjectKey() {
		return objectKey;
	}
	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}
	public String getPresignedUrl() {
		return presignedUrl;
	}
	public void setPresignedUrl(String presignedUrl) {
		this.presignedUrl = presignedUrl;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
    
    
}
