package com.santobucle.VaseDB.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.dto.request.GameRequest;
import com.santobucle.VaseDB.dto.response.GameResponse;
import com.santobucle.VaseDB.entity.Game;
import com.santobucle.VaseDB.entity.Resolution;

@Component
public class GameMapper {

    @Autowired
    private ResolutionMapper resolutionMapper;

    @Autowired
    private UserMapper userMapper;

    public GameRequest mapToGameDto(Game game) throws NullPointerException {
        List<ResolutionDto> resolutionDtoList = new ArrayList<>();
        for (Resolution resolution : game.getResolutions()) {
            resolutionDtoList.add(resolutionMapper.mapToResolutionDto(resolution));
        }

        return new GameRequest(
                game.getId(),
                // userMapper.mapToUserDto(game.getUserUuid()),
                game.getTotalTime(),
                game.getBuild().getVersion(),// BuildMapper.mapToBuildDto(game.getBuild()),
                resolutionDtoList,
                game.getDate());
    }

    public Game mapToGame(GameRequest gameRequest) throws NullPointerException {
        return new Game(
                gameRequest.getId(),
                null,
                gameRequest.getTotalTime(),
                null,//new Build(gameDto.getBuild()),// BuildMapper.mapToBuild(gameDto.getBuild()),
                null,// resolutionList,
                gameRequest.getDate());
    }

    public GameResponse mapToGameResponse(GameRequest gameDto) {
        return new GameResponse(
                gameDto.getId(),
                gameDto.getTotalTime(),
                gameDto.getDate());
    }
}
