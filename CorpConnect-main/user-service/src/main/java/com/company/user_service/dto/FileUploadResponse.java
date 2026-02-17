package com.company.user_service.dto;


public class FileUploadResponse {
    private String fileId;
    private String objectKey;
    private String uploadUrl;

    public FileUploadResponse() {}
    public FileUploadResponse(String fileId, String objectKey, String uploadUrl) {
        this.fileId = fileId; this.objectKey = objectKey; this.uploadUrl = uploadUrl;
    }

    // getters/setters
    public String getFileId() { return fileId; }
    public void setFileId(String fileId) { this.fileId = fileId; }
    public String getObjectKey() { return objectKey; }
    public void setObjectKey(String objectKey) { this.objectKey = objectKey; }
    public String getUploadUrl() { return uploadUrl; }
    public void setUploadUrl(String uploadUrl) { this.uploadUrl = uploadUrl; }
}
