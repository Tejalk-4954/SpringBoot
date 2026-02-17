package com.company.tickert_service.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * Uses Principal placed into handshake attributes (by JwtHandshakeInterceptor).
 * Ensures STOMP sessions have Principal available via Controller method signatures.
 */
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        Object u = attributes.get("user");
        if (u instanceof Principal) {
            return (Principal) u;
        }
        return super.determineUser(request, wsHandler, attributes);
    }
}
