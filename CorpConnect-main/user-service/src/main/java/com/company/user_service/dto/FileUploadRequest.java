package com.company.user_service.dto;

import jakarta.validation.constraints.NotBlank;

public class FileUploadRequest {
    @NotBlank private String fileName;
    @NotBlank private String mimeType;
    @NotBlank private String ownerType; // e.g., USER
    @NotBlank private String ownerId;

    // getters/setters
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getMimeType() { return mimeType; }
    public void setMimeType(String mimeType) { this.mimeType = mimeType; }
    public String getOwnerType() { return ownerType; }
    public void setOwnerType(String ownerType) { this.ownerType = ownerType; }
    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
}