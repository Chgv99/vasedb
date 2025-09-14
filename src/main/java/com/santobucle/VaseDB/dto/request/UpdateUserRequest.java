package com.santobucle.VaseDB.dto.request;

public record UpdateUserRequest (
    String userId,
    String userNickname
){}
