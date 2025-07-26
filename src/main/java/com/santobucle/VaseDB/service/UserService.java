package com.santobucle.VaseDB.service;

import java.util.UUID;

import com.santobucle.VaseDB.dto.request.UserRequest;
import com.santobucle.VaseDB.dto.response.UserResponse;

public interface UserService {
    public UserResponse create(UUID userUuid);
}
