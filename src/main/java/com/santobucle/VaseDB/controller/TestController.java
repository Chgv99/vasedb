package com.santobucle.VaseDB.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> test(HttpServletRequest request) {
        System.out.println(">>> /test endpoint hit from: " + request.getRemoteAddr());
        return ResponseEntity.ok()
                .header("X-API", "SpringBoot")
                .body("API is reachable");
    }

    @GetMapping("/api/test")
    public ResponseEntity<String> testAuth(HttpServletRequest request) {
        System.out.println(">>> /test endpoint hit from: " + request.getRemoteAddr());
        return ResponseEntity.ok()
                .header("X-API", "SpringBoot")
                .body("API is reachable");
    }
}
