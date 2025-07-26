package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.request.GameRequest;
import com.santobucle.VaseDB.entity.Game;

public interface GameService {
    public GameRequest createGame(GameRequest gameDto);

    public Game findGameById(Long gameId);
}
