package com.santobucle.VaseDB.service.impl;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    public String generateToken(String userId);

    // public boolean isTokenValid(String token);

    public Claims extractClaims(String token);
}
