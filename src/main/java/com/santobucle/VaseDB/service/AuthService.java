package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.request.LoginRequest;
import com.santobucle.VaseDB.dto.request.RegisterRequest;
import com.santobucle.VaseDB.dto.response.TokenResponse;

public interface AuthService {
    public TokenResponse register(RegisterRequest request);
    public TokenResponse login(LoginRequest request);
    public TokenResponse refresh();
}
