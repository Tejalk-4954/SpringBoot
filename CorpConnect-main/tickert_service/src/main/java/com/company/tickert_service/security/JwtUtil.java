package com.company.tickert_service.security;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long jwtExpirationMs;

    public JwtUtil(@Value("${security.jwt.secret}") String secret,
                   @Value("${security.jwt.expiration-ms}") long jwtExpirationMs) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationMs = jwtExpirationMs;
    }

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
               
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(getAllClaims(token));
    }

    public List<String> getRoles(String token) {
        Claims c = getAllClaims(token);
        return c.get("roles", List.class);
    }

    public boolean isExpired(String token) {
        Date exp = getClaim(token, Claims::getExpiration);
        return exp.before(new Date());
    }
}
