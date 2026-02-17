package com.company.hiring_service.service;

import com.company.hiring_service.dto.UserDto;
import com.company.hiring_service.security.UserClient;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserClient userClient;

    public AuthorizationService(UserClient userClient) {
        this.userClient = userClient;
    }

    private boolean hasRole(UserDto user, String role) {
        // user.getRoles() likely contains plain names like "MANAGER", "HR", "CANDIDATE"
        return user.getRoles().stream()
                .anyMatch(r ->
                        r.equalsIgnoreCase(role) ||
                        ("ROLE_" + r).equalsIgnoreCase(role));
    }

    public boolean canCreateJobPost(String email) {
        return userClient.getByEmail(email, null)
                .map(u -> hasRole(u, "MANAGER") || hasRole(u, "HR"))
                .orElse(false);
    }

    public boolean canApplyForJob(String email) {
        return userClient.getByEmail(email, null)
                .map(u -> hasRole(u, "CANDIDATE"))
                .orElse(false);
    }
}