package com.company.hiring_service.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.hiring_service.security.CustomUserDetailsService;
import com.company.hiring_service.security.JwtAuthenticationFilter;
import com.company.hiring_service.security.JwtUtil;
import com.company.hiring_service.security.UserClient;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final UserClient userClient;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          JwtUtil jwtUtil,
                          UserClient userClient) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.userClient = userClient;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtil, userClient);
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/api/job-posts/public").permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> logFilter() {
        FilterRegistrationBean<OncePerRequestFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
                    throws ServletException, IOException {
                System.out.println("➡️  Incoming: " + req.getMethod() + " " + req.getRequestURI());
                chain.doFilter(req, resp);
                System.out.println("⬅️  Status: " + resp.getStatus());
            }
        });
        reg.setOrder(Integer.MIN_VALUE);
        return reg;
    }
}