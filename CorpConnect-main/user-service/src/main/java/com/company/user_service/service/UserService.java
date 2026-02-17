package com.company.user_service.service;

import java.util.List;

import com.company.user_service.dto.AuthResponse;
import com.company.user_service.dto.FileUploadRequest;
import com.company.user_service.dto.FileUploadResponse;
import com.company.user_service.dto.LoginRequest;
import com.company.user_service.dto.RegisterRequest;
import com.company.user_service.dto.UserDto;

public interface UserService {
    UserDto register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserDto getById(String id);
    List<UserDto> listAll(int page, int size);
    void assignRole(String userId, String roleName);
    FileUploadResponse presignUpload(FileUploadRequest req);
    void confirmUpload(String fileId);
	UserDto getUserByEmail(String email);
	void updateDepartment(String id,String department);
	List<UserDto> getEmployeesUnderHead(String headId);
    List<UserDto> getEmployeesByDepartment(String department);
    List<UserDto> getDepartmentHeads();
    String getDepartmentByUserId(String username);
   // List<UserDto> getAllUsers();
}