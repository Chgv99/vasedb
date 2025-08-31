package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.request.GameRequest;
import com.santobucle.VaseDB.dto.response.GameResponse;
import com.santobucle.VaseDB.entity.Game;

public interface GameService {
    public GameResponse createGame(GameRequest gameDto);

    public Game findGameById(Long gameId);
}
