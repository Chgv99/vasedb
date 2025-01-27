package com.santobucle.VaseDB.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santobucle.VaseDB.dto.GameDto;
import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.entity.Game;
import com.santobucle.VaseDB.entity.Resolution;
import com.santobucle.VaseDB.service.ResolutionService;

@Component
public class GameMapper {

    @Autowired
    private ResolutionService resolutionService;

    @Autowired
    private ResolutionMapper resolutionMapper;

    public GameDto mapToGameDto(Game game) throws NullPointerException {
        List<ResolutionDto> resolutionDtoList = new ArrayList<>();
        for (Resolution resolution : game.getResolutions()) {
            resolutionDtoList.add(resolutionMapper.mapToResolutionDto(resolution));
        }

        return new GameDto(
            game.getId(),
            game.getTotalTime(),
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
            resolutionList,
            gameDto.getDate()
        );
    }
}
