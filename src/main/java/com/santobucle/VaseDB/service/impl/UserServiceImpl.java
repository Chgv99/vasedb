package com.santobucle.VaseDB.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.response.UserResponse;
import com.santobucle.VaseDB.entity.User;
import com.santobucle.VaseDB.exception.ResourceNotFoundException;
import com.santobucle.VaseDB.repository.UserRepository;
import com.santobucle.VaseDB.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserResponse create(UUID userUuid) {
        UserResponse response;
        try {
            response = getUserByUuid(userUuid);
        } catch (Exception e) {
            User user = new User(userUuid, null, new Date());
            User savedUser = userRepository.save(user);
            response = new UserResponse(savedUser.getUuid(), savedUser.getNickname(), savedUser.getCreatedAt());
        }

        return response;
    }

    public UserResponse getUserByUuid(UUID uuid) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("User with uuid " + uuid + " does not exist."));
        return new UserResponse(user.getUuid(), user.getNickname(), user.getCreatedAt());
    }

    @Override
    public UserResponse updateUserNickname(String userUuid, String nickname) {
        User user = userRepository.findById(UUID.fromString(userUuid))
                .orElseThrow(() -> new ResourceNotFoundException("User with uuid " + userUuid + " does not exist."));
        user.setNickname(nickname);
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getUuid(), savedUser.getNickname(), savedUser.getCreatedAt());
    }
}
