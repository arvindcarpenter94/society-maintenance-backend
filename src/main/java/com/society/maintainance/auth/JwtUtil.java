package com.society.maintenance.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key key;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(decodedKey);
    }

    // ✅ TOKEN GENERATION (unchanged)
    public String generateToken(com.society.maintenance.user.User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .claim("flatId",
                        user.getFlat() != null ? user.getFlat().getId() : null)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ PARSE CLAIMS
    public Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ REQUIRED BY FILTER
    public String extractUsername(String token) {
        return parse(token).getSubject();
    }

    // ✅ REQUIRED BY FILTER
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = parse(token);
            String username = claims.getSubject();
            Date expiry = claims.getExpiration();

            return username.equals(userDetails.getUsername())
                    && expiry.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
