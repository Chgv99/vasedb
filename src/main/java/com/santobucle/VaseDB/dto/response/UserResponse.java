package com.santobucle.VaseDB.dto.response;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private UUID uuid;
    private String nickname;
    private Date createdAt;
}
