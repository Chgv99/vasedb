package com.santobucle.VaseDB.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santobucle.VaseDB.dto.GameDto;
import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.dto.response.GameResponse;
import com.santobucle.VaseDB.entity.Game;
import com.santobucle.VaseDB.entity.Resolution;
import com.santobucle.VaseDB.service.ResolutionService;

@Component
public class GameMapper {

    @Autowired
    private ResolutionMapper resolutionMapper;

    @Autowired
    private ResolutionService resolutionService;

    public GameDto mapToGameDto(Game game) throws NullPointerException {
        List<ResolutionDto> resolutionDtoList = new ArrayList<>();
        for (Resolution resolution : game.getResolutions()) {
            resolutionDtoList.add(resolutionMapper.mapToResolutionDto(resolution));
        }

        return new GameDto(
            game.getId(),
            game.getTotalTime(),
            BuildMapper.mapToBuildDto(game.getBuild()),
            resolutionDtoList,
            game.getDate()
        );
    }
    
    public Game mapToGame(GameDto gameDto) throws NullPointerException {
        List<Resolution> resolutionList = new ArrayList<>();

        for (ResolutionDto resolutionDto : gameDto.getResolutions()) {
            Resolution resolution = resolutionMapper.mapToResolution(resolutionDto);
            resolutionList.add(resolution);
        }

        return new Game(
                gameDto.getId(),
                gameDto.getTotalTime(),
                BuildMapper.mapToBuild(gameDto.getBuild()),
                resolutionList,
                gameDto.getDate());
    }
    
    public GameResponse mapToGameResponse(GameDto gameDto) {
        return new GameResponse(
            gameDto.getId(),
            gameDto.getTotalTime(),
            gameDto.getDate()
        );
    }
}
