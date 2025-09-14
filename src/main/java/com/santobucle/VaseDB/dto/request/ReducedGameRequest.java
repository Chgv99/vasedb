package com.santobucle.VaseDB.dto.request;

import java.util.UUID;

public record ReducedGameRequest (
    Long id,
    UUID userId,
    String userNickname,
    int score,
    float totalTime,
    String build
) {}
