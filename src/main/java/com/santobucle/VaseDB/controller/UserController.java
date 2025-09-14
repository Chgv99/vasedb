package com.santobucle.VaseDB.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santobucle.VaseDB.dto.request.UpdateUserRequest;
import com.santobucle.VaseDB.dto.response.UserResponse;
import com.santobucle.VaseDB.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return new ResponseEntity<>(
                userService.updateUserNickname(updateUserRequest.userId(), updateUserRequest.userNickname()),
                HttpStatus.OK);
    }

}
