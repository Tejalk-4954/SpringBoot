package com.company.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="user_roles")
@IdClass(UserRoleId.class)
public class UserRole {

    @Id
    @Column(length=36)
    private String userId;

    @Id
    @Column(length=36)
    private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

    // PrePersist not needed; composite PK
    // getters/setters
    
    
}
