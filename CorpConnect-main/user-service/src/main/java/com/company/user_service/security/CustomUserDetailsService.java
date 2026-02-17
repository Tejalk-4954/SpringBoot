package com.company.user_service.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.company.user_service.entity.Role;
import com.company.user_service.entity.User;
import com.company.user_service.repo.RoleRepository;
import com.company.user_service.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CustomUserDetailsService(UserRepository userRepository,
                                    RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findByEmail(email);
        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        User user = opt.get();
        List<GrantedAuthority> authorities = loadAuthorities(user);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }

    private List<GrantedAuthority> loadAuthorities(User user) {
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null) roles = Collections.emptyList();

        return roles.stream()
                .map(Role::getName) // e.g. "EMPLOYEE", "ADMIN", "MANAGER"
                .map(name -> name.startsWith("ROLE_") ? name : "ROLE_" + name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}