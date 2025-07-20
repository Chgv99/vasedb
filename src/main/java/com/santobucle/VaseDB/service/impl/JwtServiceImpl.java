package com.santobucle.VaseDB.service.impl;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private int EXPIRATION_MS;

    @Override
    public String generateToken(String userId) {
        return Jwts.builder()
                .subject(userId)
                // .claim("userId", userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSignInKey())
                .compact();
    }

    // public boolean isTokenValid(String token) {
    //     SecretKey secretKey = (SecretKey) getSignInKey();
    //     try {
    //         Jwts.builder()
    //             .signWith(getSignInKey())
    //             .parseClaimsJws(token);
    //         return true;
    //     } catch (JwtException e) {
    //         return false;
    //     }
    // }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
