package com.santobucle.VaseDB.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterRequest(
    @JsonProperty("user_uuid")
    String userUuid
) {}
