package com.pgFinder.PgFinder.entity;

	import jakarta.persistence.*;

	@Entity
	public class Pg {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String ownerName;
	    private String phoneNumber;
	    private String roomPhotoPath; // Path to uploaded photo

	    // Getters, setters, constructors
	    public Pg() {}
	    public Pg(String ownerName, String phoneNumber, String roomPhotoPath) {
	        this.ownerName = ownerName;
	        this.phoneNumber = phoneNumber;
	        this.roomPhotoPath = roomPhotoPath;
	    }

	    // Getters and setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getOwnerName() { return ownerName; }
	    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
	    public String getPhoneNumber() { return phoneNumber; }
	    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	    public String getRoomPhotoPath() { return roomPhotoPath; }
	    public void setRoomPhotoPath(String roomPhotoPath) { this.roomPhotoPath = roomPhotoPath; }
	}

