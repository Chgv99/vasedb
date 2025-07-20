package com.santobucle.VaseDB.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterRequest(
    @JsonProperty("user_id")
    String userId
) {}
