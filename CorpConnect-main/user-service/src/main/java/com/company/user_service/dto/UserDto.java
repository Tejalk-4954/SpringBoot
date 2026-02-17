package com.company.user_service.dto;

import java.util.List;

public class UserDto {
	  private String id;
	  private String fullName;
	  private String email;
	 // private String phone;
	  private String profileFileId;
	  private String department;
	  public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	// getters/setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfileFileId() {
		return profileFileId;
	}
	public void setProfileFileId(String profileFileId) {
		this.profileFileId = profileFileId;
	}
	  
	private List<String> roles;

    // ✅ Getter & Setter for roles
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
	public UserDto(String id, String fullName, String email, String profileFileId, List<String> roles) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.profileFileId = profileFileId;
		this.roles = roles;
	}
	public UserDto() {
		super();
	}
    
    
	}