package com.company.hiring_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserClient userClient;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserClient userClient) {
        this.jwtUtil = jwtUtil;
        this.userClient = userClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse resp,
                                    FilterChain chain)
            throws ServletException, IOException {

        String path = req.getRequestURI();

        // Public endpoints – skip JWT processing
        if (path.contains("/public") || path.startsWith("/actuator") || path.startsWith("/error")) {
            chain.doFilter(req, resp);
            return;
        }

        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, resp);
            return;
        }

        String token = header.substring(7);

        try {
            String email = jwtUtil.getUsername(token);
            List<String> rolesFromJwt = jwtUtil.getRoles(token);

            List<SimpleGrantedAuthority> authorities =
                    rolesFromJwt.stream()
                            .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                            .toList();

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(email, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception ex) {
            System.out.println("❌ JWT Auth Error: " + ex.getMessage());
        }

        chain.doFilter(req, resp);
    }
}