package com.santobucle.VaseDB.dto.response;

import java.util.UUID;

public record ReducedGameResponse (
    Long id,
    UUID userId,
    String userNickname,
    int score,
    float totalTime,
    String build
) {}
