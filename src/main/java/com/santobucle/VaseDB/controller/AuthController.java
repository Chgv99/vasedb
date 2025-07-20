package com.santobucle.VaseDB.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santobucle.VaseDB.dto.request.LoginRequest;
import com.santobucle.VaseDB.dto.request.RegisterRequest;
import com.santobucle.VaseDB.dto.response.TokenResponse;
import com.santobucle.VaseDB.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> getToken(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    // @PostMapping("/login")
    // public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginRequest request) {
    //     return ResponseEntity.ok(authService.login(request));
    // }

    // @PostMapping("/refresh")
    // public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
    //     // return ResponseEntity.ok(authService.refresh(request));
    //     return authService.refreshToken(authHeader);
    // }
}
