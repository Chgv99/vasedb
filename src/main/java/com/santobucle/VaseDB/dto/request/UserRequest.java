package com.santobucle.VaseDB.dto.request;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {
    private UUID uuid;
    private String nickname;
    private Date createdAt;
}
