package com.santobucle.VaseDB.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.GameDto;
import com.santobucle.VaseDB.entity.Build;
import com.santobucle.VaseDB.entity.Game;
import com.santobucle.VaseDB.entity.Resolution;
import com.santobucle.VaseDB.entity.Stage;
import com.santobucle.VaseDB.entity.StageBuild;
import com.santobucle.VaseDB.mapper.GameMapper;
import com.santobucle.VaseDB.mapper.ResolutionMapper;
import com.santobucle.VaseDB.repository.GameRepository;
import com.santobucle.VaseDB.service.BuildService;
import com.santobucle.VaseDB.service.GameService;
import com.santobucle.VaseDB.service.ResolutionService;
import com.santobucle.VaseDB.service.StageBuildService;
import com.santobucle.VaseDB.service.StageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    private ResolutionService resolutionService;

    private ResolutionMapper resolutionMapper;

    private StageBuildService stageBuildService;

    private StageService stageService;

    private BuildService buildService;

    private GameMapper gameMapper;

    public GameDto createGame(GameDto gameDto) {
        return save(gameMapper.mapToGame(gameDto));
    }

    private GameDto save(Game game) {
        gameRepository.save(game);

        List<Resolution> savedResolutions = new ArrayList<Resolution>();
        for (Resolution resolution : game.getResolutions()) {
            resolution.setGame(game);

            StageBuild stageBuild = resolution.getStageBuild();

            // Check if StageBuild exists
            StageBuild existingStageBuild = stageBuildService.getStageBuild(stageBuild);
            if (existingStageBuild != null) {
                resolution.setStageBuild(existingStageBuild);
                savedResolutions.add(resolutionService.saveWithJsonCast(resolution));
                continue;
            }

            // TODO: Llevar esta lógica a los servicios correspondientes
            // Check if Stage exists
            Stage stage = stageService.getStage(stageBuild.getStage());
            if (stage != null) {
                stageBuild.setStage(stage);
            }

            // Check if Build exists
            Build build = buildService.getBuild(stageBuild.getBuild());
            if (build != null) {
                stageBuild.setBuild(build);
            }

            // Save StageBuild with associated Stage and Build
            StageBuild savedStageBuild = stageBuildService.saveWithJsonCast(stageBuild);

            resolution.setStageBuild(savedStageBuild);
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
