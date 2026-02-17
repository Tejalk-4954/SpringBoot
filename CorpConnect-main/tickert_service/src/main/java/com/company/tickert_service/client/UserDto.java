package com.company.tickert_service.client;

import java.util.List;

import lombok.Data;
@Data
public class UserDto {
    private String id;
    private String fullName;
    private String email;
    private List<String> roles;
    // getters/setters
    // default ctor
	public UserDto() {
		super();
	}
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
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
    
    
}
