package com.pgFinder.PgFinder.dto;



public class PgDto {
    private Long id;
    private String ownerName;
    private String phoneNumber;
    private String roomPhotoPath;

    // Default constructor
    public PgDto() {}

    // Parameterized constructor
    public PgDto(Long id, String ownerName, String phoneNumber, String roomPhotoPath) {
        this.id = id;
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.roomPhotoPath = roomPhotoPath;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoomPhotoPath() {
        return roomPhotoPath;
    }

    public void setRoomPhotoPath(String roomPhotoPath) {
        this.roomPhotoPath = roomPhotoPath;
    }
}