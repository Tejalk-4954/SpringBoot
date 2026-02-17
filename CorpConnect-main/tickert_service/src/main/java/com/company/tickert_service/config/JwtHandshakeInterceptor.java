package com.company.tickert_service.config;

import java.security.Principal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Extracts JWT from Authorization header or token query param, validates it,
 * and stores a Principal into handshake attributes under key "user".
 */
@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtHandshakeInterceptor.class);

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest servletReq) {
            HttpServletRequest httpReq = servletReq.getServletRequest();
            String token = null;

            String auth = httpReq.getHeader("Authorization");
            if (auth != null && auth.startsWith("Bearer ")) {
                token = auth.substring(7);
            }

            if (token == null) {
                token = httpReq.getParameter("token");
            }

            if (token != null && !token.isBlank()) {
                try {
                    Claims claims = Jwts.parserBuilder()
                                        .setSigningKey(jwtSecret.getBytes())
                                        .build()
                                        .parseClaimsJws(token)
                                        .getBody();
                    String subject = claims.getSubject();
                    if (subject == null || subject.isBlank()) {
                        log.warn("Token parsed but subject is empty");
                        return false;
                    }
                    Principal user = new StompPrincipal(subject);
                    attributes.put("user", user);
                    log.debug("WebSocket handshake authenticated principal: {}", subject);
                    return true;
                } catch (SecurityException se) {
                    log.warn("Invalid JWT signature during WS handshake: {}", se.getMessage());
                    return false;
                } catch (Exception ex) {
                    log.warn("JWT validation error during WS handshake: {}", ex.getMessage());
                    return false;
                }
            }
        }
        // allow anonymous connections (change to false to require auth)
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
        // no-op
    }
}
