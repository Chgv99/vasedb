package com.santobucle.VaseDB.mapper;

import org.springframework.stereotype.Component;

import com.santobucle.VaseDB.dto.request.UserRequest;
import com.santobucle.VaseDB.dto.response.UserResponse;
import com.santobucle.VaseDB.entity.User;

@Component
public class UserMapper {
    
    public UserResponse mapToUserDto(User user) {
        return new UserResponse(user.getUuid(), user.getNickname(), user.getCreatedAt());
    }

    public User mapToUser(UserRequest userRequest) {
        return new User(userRequest.getUuid(), userRequest.getNickname(), userRequest.getCreatedAt());
    }

    public User mapToUser(UserResponse userResponse) {
        return new User(userResponse.getUuid(), userResponse.getNickname(), userResponse.getCreatedAt());
    }
}
