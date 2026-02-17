package com.company.user_service.controller;

import java.util.List;
import java.util.Map;

import com.company.user_service.dto.FileUploadRequest;
import com.company.user_service.dto.FileUploadResponse;
import com.company.user_service.dto.UserDto;
import com.company.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    // ---- Current user ----
    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        UserDto user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    // ---- Get user by ID (admin only) ----
    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getById(@PathVariable String id) {
        UserDto dto = userService.getById(id);
        return ResponseEntity.ok(dto);
    }

    // ---- List all users (paged, admin only) ----
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        List<UserDto> list = userService.listAll(page, size);
        return ResponseEntity.ok(list);
    }

    // ---- Assign multiple roles to a user (admin) ----
    @PostMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> assignRoles(@PathVariable String id,
                                            @RequestBody Map<String, List<String>> body) {
        List<String> roles = body.get("roles");
        if (roles != null) {
            roles.forEach(role -> userService.assignRole(id, role));
        }
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/department")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateDepartment(
            @PathVariable String id,
            @RequestBody Map<String, String> body) {

        String department = body.get("department");

        if (department != null && !department.isBlank()) {
            userService.updateDepartment(id, department);
        }

        return ResponseEntity.noContent().build();
    }

    
    

    // ---- File upload (profile / docs) ----
    @PostMapping("/files/presign")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<FileUploadResponse> presignUpload(@Valid @RequestBody FileUploadRequest req) {
        FileUploadResponse resp = userService.presignUpload(req);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/files/complete")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> completeUpload(@RequestBody Map<String,String> body) {
        String fileId = body.get("fileId");
        userService.confirmUpload(fileId);
        return ResponseEntity.ok().build();
    }

    // ---- Domain helper APIs for ticket / assignment flow ----

    // 1) list employees under a head (admin or head-type roles)
    @GetMapping("/head/{headId}/employees")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','HR_HEAD','ITS_HEAD')")
    public ResponseEntity<List<UserDto>> employeesUnderHead(@PathVariable String headId) {
        List<UserDto> list = userService.getEmployeesUnderHead(headId);
        return ResponseEntity.ok(list);
    }

    // 2) list employees by department (admin or head-type roles)
    @GetMapping("/department/{dept}/employees")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','HR_HEAD','ITS_HEAD')")
    public ResponseEntity<List<UserDto>> employeesByDepartment(@PathVariable String dept) {
        List<UserDto> list = userService.getEmployeesByDepartment(dept);
        return ResponseEntity.ok(list);
    }

    // 3) list all department heads (admin only)
    @GetMapping("/department-heads")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> departmentHeads() {
        return ResponseEntity.ok(userService.getDepartmentHeads());
    }

    // 4) get head for a specific department
    @GetMapping("/department/{dept}/head")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','HR_HEAD','ITS_HEAD')")
    public ResponseEntity<UserDto> departmentHead(@PathVariable String dept) {
        List<UserDto> heads = userService.getDepartmentHeads();
        return heads.stream()
                .filter(h -> dept.equalsIgnoreCase(h.getDepartment()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---- Lookup by email (internal use by other services) ----
    @GetMapping("/email/{email}")
    @PreAuthorize("isAuthenticated()")
    public UserDto getByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
    
    @GetMapping("/{id}/department")
    public String getDepartment(@PathVariable String id) {
        return userService.getDepartmentByUserId(id);
    }
    
    
}