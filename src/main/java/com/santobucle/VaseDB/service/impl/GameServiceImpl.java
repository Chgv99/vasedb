package com.santobucle.VaseDB.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.dto.GameDto;
import com.santobucle.VaseDB.entity.Game;
import com.santobucle.VaseDB.entity.Resolution;
import com.santobucle.VaseDB.mapper.BuildMapper;
import com.santobucle.VaseDB.mapper.GameMapper;
import com.santobucle.VaseDB.repository.GameRepository;
import com.santobucle.VaseDB.service.BuildService;
import com.santobucle.VaseDB.service.GameService;
import com.santobucle.VaseDB.service.ResolutionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    private ResolutionService resolutionService;

    private BuildService buildService;

    private GameMapper gameMapper;

    public GameDto createGame(GameDto gameDto) {
        return save(gameMapper.mapToGame(gameDto));
    }

    private GameDto save(Game game) {
        BuildDto buildDto = buildService.createBuild(game.getBuild());

        game.setBuild(BuildMapper.mapToBuild(buildDto));
        Game savedGame = gameRepository.save(game);

        List<Resolution> savedResolutions = new ArrayList<Resolution>();
        for (Resolution resolution : game.getResolutions()) {
            resolution.setGame(savedGame);

            savedResolutions.add(resolutionService.saveWithJsonCast(resolution));
        }
        game.getResolutions().clear(); // Clear the existing collection
        game.getResolutions().addAll(savedResolutions); // Add the new elements
        GameDto gameDto = gameMapper.mapToGameDto(gameRepository.save(game));
        return gameDto;
    }

    @Override
    public Game findGameById(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

}
