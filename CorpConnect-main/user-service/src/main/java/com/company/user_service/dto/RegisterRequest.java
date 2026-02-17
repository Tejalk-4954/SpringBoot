package com.company.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank
    private String fullName;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String password;
//    @NotBlank
//    private String department;
    // getters/setters
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RegisterRequest(@NotBlank String fullName, @Email @NotBlank String email, @NotBlank String password, @NotBlank String dept) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.password = password;
//		this.department = dept;
	}
	public RegisterRequest() {
		super();
	}
//	public String getDepartment() {
//		return department;
//	}
//	public void setDepartment(String department) {
//		this.department = department;
//	}
	
	
    
    
}
