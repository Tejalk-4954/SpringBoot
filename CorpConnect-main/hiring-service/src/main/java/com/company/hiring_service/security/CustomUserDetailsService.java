package com.company.hiring_service.security;

import com.company.hiring_service.dto.UserDto;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserClient userClient;

    public CustomUserDetailsService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var userOpt = userClient.getByEmail(username, null);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        UserDto u = userOpt.get();

        return User.builder()
                .username(u.getEmail())
                .password("") // password not checked (JWT only)
                .authorities(
                        u.getRoles().stream()
                                .map(r -> "ROLE_" + r)
                                .collect(Collectors.toList())
                                .toArray(new String[0])
                )
                .build();
    }
}