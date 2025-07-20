package com.santobucle.VaseDB.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.request.LoginRequest;
import com.santobucle.VaseDB.dto.request.RegisterRequest;
import com.santobucle.VaseDB.dto.response.TokenResponse;
import com.santobucle.VaseDB.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final JwtService jwtService;

    @Override
    public TokenResponse register(RegisterRequest request) {
        String userId = request.userId();
        if (request.userId() == null || request.userId().trim().isEmpty()) {
            userId = UUID.randomUUID().toString();  // generate unique ID
        }
        return new TokenResponse(jwtService.generateToken(userId));
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public TokenResponse refresh() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refresh'");
    }   
    
}