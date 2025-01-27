package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.GameDto;
import com.santobucle.VaseDB.entity.Game;

public interface GameService {
    public GameDto createGame(GameDto gameDto);

    public Game findGameById(Long gameId);
}
