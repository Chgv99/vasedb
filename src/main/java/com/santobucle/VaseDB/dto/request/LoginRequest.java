package com.santobucle.VaseDB.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequest (
    @JsonProperty("user_id")
    String userId
) {}
