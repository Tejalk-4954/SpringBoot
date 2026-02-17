package com.company.tickert_service.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.tickert_service.client.UserClient;
import com.company.tickert_service.client.UserDto; // simple DTO

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserClient userClient;

    public CustomUserDetailsService(UserClient userClient) {
        this.userClient = userClient;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // username is email
//        UserDto user = userClient.getByEmail(username);
//        if (user == null) throw new UsernameNotFoundException("User not found: " + username);
//
//        var authorities = user.getRoles() == null ? List.of() :
//                user.getRoles().stream().map(r -> new org.springframework.security.core.authority.SimpleGrantedAuthority(r)).collect(Collectors.toList());
//
//        // password is not available; return a UserDetails with username and empty password (filter uses token).
//        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
//                .password("") // no password here; token-based
//                .authorities(new ArrayList<>(authorities))
//                .accountExpired(false)
//                .accountLocked(false)
//                .credentialsExpired(false)
//                .disabled(false)
//                .build();
//    }
//}
    
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username = email
        UserDto user = userClient.getByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User not found: " + username);

        List<GrantedAuthority> authorities = user.getRoles() == null ? List.of() :
                user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password("") // we use JWT, so no password needed here
                .authorities(new ArrayList<>(authorities)) // ✅ FIX
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
    
    
    
