package com.santobucle.VaseDB.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.dto.GameDto;
import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.entity.Build;
import com.santobucle.VaseDB.entity.Game;
import com.santobucle.VaseDB.entity.Resolution;
import com.santobucle.VaseDB.mapper.BuildMapper;
import com.santobucle.VaseDB.mapper.GameMapper;
import com.santobucle.VaseDB.mapper.ResolutionMapper;
import com.santobucle.VaseDB.repository.GameRepository;
import com.santobucle.VaseDB.service.BuildService;
import com.santobucle.VaseDB.service.GameService;
import com.santobucle.VaseDB.service.ResolutionService;
import com.santobucle.VaseDB.service.StageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    private ResolutionService resolutionService;

    private StageService stageService;

    private BuildService buildService;

    private GameMapper gameMapper;

    private ResolutionMapper resolutionMapper;

    /** Handles game creation and persistence
     *  Note: We store the game before handling resolutions
     *  because we cannot rely on JPA cascading, as we need
     *  some JSON treatment on the repository. And to do that
     *  we need to save the resolutions manually with a gameId. */
    public GameDto createGame(GameDto gameDto) {
        // Save the game before handling build and resolutions
        Game savedGame = gameRepository.save(gameMapper.mapToGame(gameDto));

        // Assign build (stored or new) to the gameDto
        BuildDto buildDto = buildService.createBuild(gameDto.getBuild());
        Build savedBuild = BuildMapper.mapToBuild(buildDto);
        // gameDto.setBuild(buildDto.getVersion());

        List<Resolution> updatedResolutions = new ArrayList<Resolution>();
        for (ResolutionDto resolutionDto : gameDto.getResolutions()) {
            // Assign saved gameId
            resolutionDto.setGameId(savedGame.getId());

            // Stage handled inside saveWithJsonCast
            ResolutionDto savedResolutionDto = resolutionService.saveWithJsonCast(resolutionDto);
            updatedResolutions.add(resolutionMapper.mapToResolution(savedResolutionDto));
        }

        // Create an updated version of the saved game
        Game game = new Game(
            savedGame.getId(),
            savedGame.getTotalTime(),
            savedBuild,// BuildMapper.mapToBuild(buildDto),
            updatedResolutions,
            savedGame.getDate()
        );

        // Store the updated game
        Game updatedSavedGame = gameRepository.save(game);
        return gameMapper.mapToGameDto(updatedSavedGame);
    }

    @Override
    public Game findGameById(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

}
