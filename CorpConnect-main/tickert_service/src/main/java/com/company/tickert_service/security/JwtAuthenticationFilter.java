package com.company.tickert_service.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header == null || !header.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String token = header.substring(7);
//        try {
//            String username = jwtUtil.getUsername(token);
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                // call userDetails (we will implement user client to fetch minimal details)
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                if (!jwtUtil.isExpired(token)) {
//                    List<String> roles = jwtUtil.getRoles(token);
//                    var authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//
//                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
//                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//                }
//            }
//        } catch (Exception ex) {
//            // invalid token, ignore -> no authentication
//        }
//
//        filterChain.doFilter(request, response);
//    }
    
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);

        try {
            String username = jwtUtil.getUsername(token);
            List<String> roles = jwtUtil.getRoles(token); // ✅ get roles from JWT

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // ✅ Make sure roles are not null
                if (roles == null) {
                    roles = List.of();
                }

                // ✅ Convert roles into authorities
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r) // ensures ROLE_ prefix
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                // ✅ Set Authentication
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);

                System.out.println("✅ JWT AUTH SUCCESS for: " + username);
                System.out.println("🎭 Roles from JWT Applied: " + authorities);
            }
        } catch (Exception ex) {
            System.out.println("❌ JWT Authentication Failed: " + ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }

}
