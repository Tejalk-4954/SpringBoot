package com.company.hiring_service.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String email;
    private List<String> roles;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
