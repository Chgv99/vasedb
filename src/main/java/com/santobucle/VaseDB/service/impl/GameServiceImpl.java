package com.santobucle.VaseDB.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.dto.GameDto;
import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.entity.Game;
import com.santobucle.VaseDB.mapper.BuildMapper;
import com.santobucle.VaseDB.mapper.GameMapper;
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

    public GameDto createGame(GameDto gameDto) {
        return save(gameMapper.mapToGame(gameDto));
    }

    private GameDto save(Game game) {
        BuildDto buildDto = buildService.createBuild(game.getBuild());

        game.setBuild(BuildMapper.mapToBuild(buildDto));
        GameDto savedGameDto = gameMapper.mapToGameDto(gameRepository.save(game));

        List<ResolutionDto> savedResolutionDtos = new ArrayList<ResolutionDto>();
        for (ResolutionDto resolutionDto : savedGameDto.getResolutions()) {
            resolutionDto.setGameId(savedGameDto.getId());

            StageDto savedStageDto = stageService.createStage(resolutionDto.getStageDto());
            resolutionDto.setStageDto(savedStageDto);
            
            savedResolutionDtos.add(resolutionService.saveWithJsonCast(resolutionDto));
        }
        savedGameDto.getResolutions().clear(); // Clear the existing collection
        savedGameDto.getResolutions().addAll(savedResolutionDtos); // Add the new elements

        Game savedGameDtoWithDetails = gameRepository.save(gameMapper.mapToGame(savedGameDto));
        return gameMapper.mapToGameDto(savedGameDtoWithDetails);
    }

    @Override
    public Game findGameById(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

}
