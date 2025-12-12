package com.gokul.ai.ai_ecommerce.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 256-bit secret key (HS256 requires 32+ characters)
    private final String SECRET =
            "2c9f0e41a8e046fa9adbcfe6a1f77ab8af4b050b84eac7d8e8236a7a8fe92d2b";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // ------------------------------------------------------------
    // 1. EXTRACT USERNAME
    // ------------------------------------------------------------
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ------------------------------------------------------------
    // 2. EXTRACT USER ID (NEWLY ADDED)
    // ------------------------------------------------------------
    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        Object userId = claims.get("userId");

        if (userId == null) {
            throw new RuntimeException("Token does not contain userId");
        }

        return Long.parseLong(userId.toString());
    }

    // ------------------------------------------------------------
    // 3. VALIDATE TOKEN
    // ------------------------------------------------------------
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isExpired(token));
    }

    // ------------------------------------------------------------
    // 4. GENERATE TOKEN (UPDATED TO INCLUDE userId)
    // ------------------------------------------------------------
    public String generateToken(UserDetails userDetails, Long userId) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())  // email
                .claim("roles", userDetails.getAuthorities())
                .claim("userId", userId)  // 🔥 VERY IMPORTANT
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ------------------------------------------------------------
    // 5. CHECK IF EXPIRED
    // ------------------------------------------------------------
    private boolean isExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // ------------------------------------------------------------
    // 6. EXTRACT CLAIMS
    // ------------------------------------------------------------
    private Claims extractAllClaims(String token) {
        // Remove "Bearer " if included
        token = token.replace("Bearer ", "");

        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
