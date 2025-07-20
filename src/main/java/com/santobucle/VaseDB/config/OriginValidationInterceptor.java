package com.santobucle.VaseDB.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OriginValidationInterceptor implements HandlerInterceptor {

    private final SecurityProperties securityProperties;

    public OriginValidationInterceptor(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
        String origin = request.getHeader("Origin");

        if (origin == null || !origin.equals(securityProperties.getVasedbClientUrl())) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Forbidden origin");
            return false;
        }

        return true;
    }
}
