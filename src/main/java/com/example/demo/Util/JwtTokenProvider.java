package com.example.demo.Util;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component

public class JwtTokenProvider {
    private static final String SECRET_KEY = "b9f8d7e6c5b4a3f2b1c0d9e8f7a6b5c4"; // 32+ chars secret key
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Get email (subject) from token
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // 🔹 Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            System.out.println("Token expired");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT");
        } catch (MalformedJwtException ex) {
            System.out.println("Malformed JWT");
        } catch (SecurityException | IllegalArgumentException ex) {
            System.out.println("Invalid JWT");
        }
        return false;
    }

}
