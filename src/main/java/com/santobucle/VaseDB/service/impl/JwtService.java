package com.santobucle.VaseDB.service.impl;

import org.springframework.stereotype.Service;

public interface JwtService {
    public String generateToken(String userId);

    // public boolean isTokenValid(String token);

    public String extractUserId(String token);
}
