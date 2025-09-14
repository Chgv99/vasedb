package com.santobucle.VaseDB.service;

import java.util.UUID;

import com.santobucle.VaseDB.dto.response.UserResponse;

public interface UserService {
    public UserResponse create(UUID userUuid);
    public UserResponse updateUserNickname(String userUuid, String nickname);
}
