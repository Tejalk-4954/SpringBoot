package com.company.tickert_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {
    private final RestTemplate restTemplate;
    private final String userServiceBase = "http://localhost:8081/api";

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto getById(String id) {
        try {
            return restTemplate.getForObject(userServiceBase + "/users/" + id, UserDto.class);
        } catch (Exception ex) {
            return null;
        }
    }

    public UserDto getByEmail(String email) {
        try {
            return restTemplate.getForObject(userServiceBase + "/users/by-email?email=" + email, UserDto.class);
        } catch (Exception ex) {
            return null;
        }
    }
}
