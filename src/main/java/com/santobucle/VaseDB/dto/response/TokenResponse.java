package com.santobucle.VaseDB.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse (
    @JsonProperty("access_token")
    String accessToken,
    @JsonProperty("user_uuid")
    String userUuid
    // @JsonProperty("refresh_token")
    // String refreshToken
) {}
