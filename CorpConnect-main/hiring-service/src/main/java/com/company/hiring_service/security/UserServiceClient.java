package com.company.hiring_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.company.hiring_service.dto.UserDto;

@Deprecated // Prefer using UserClient which reads base URL from configuration
@Component
public class UserServiceClient {

    private final RestTemplate restTemplate;
    private final String userServiceBaseUrl = "http://localhost:8082/api/users";

    @Autowired
    public UserServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto getUserByEmail(String email) {
        String url = userServiceBaseUrl + "/email/{email}";
        return restTemplate.getForObject(url, UserDto.class, email);
    }
}