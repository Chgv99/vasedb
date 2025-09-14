package com.santobucle.VaseDB.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.dto.response.GameResponse;
import com.santobucle.VaseDB.dto.response.ReducedGameResponse;
import com.santobucle.VaseDB.entity.Game;
import com.santobucle.VaseDB.entity.Resolution;

@Component
public class GameMapper {

    @Autowired
    private ResolutionMapper resolutionMapper;

    @Autowired
    private UserMapper userMapper;

    public GameResponse mapToGameDto(Game game, int ranking, String userNickname) throws NullPointerException {
        List<ResolutionDto> resolutionDtoList = new ArrayList<>();
        for (Resolution resolution : game.getResolutions()) {
            resolutionDtoList.add(resolutionMapper.mapToResolutionDto(resolution));
        }

        return new GameResponse(
                game.getId(),
                userMapper.mapToUserDto(game.getUser()),
                game.getTotalTime(),
                game.getScore(),
                ranking,
                game.isHiScore(),
                userNickname,
                game.getBuild().getVersion(),// BuildMapper.mapToBuildDto(game.getBuild()),
                resolutionDtoList,
                game.getDate());
    }

    public ReducedGameResponse mapToReducedGameResponse(Game game) {
        return new ReducedGameResponse(
            game.getId(),
            game.getUser().getUuid(),
            game.getUser().getNickname(),
            game.getScore(),
            game.getTotalTime(),
            game.getBuild().getVersion()
        );
    }

    // public Game mapToGame(GameRequest gameRequest) throws NullPointerException {
    //     return new Game(
    //             gameRequest.getId(),
    //             null,
    //             gameRequest.getTotalTime(),
    //             null,//new Build(gameDto.getBuild()),// BuildMapper.mapToBuild(gameDto.getBuild()),
    //             null,// resolutionList,
    //             gameRequest.getDate());
    // }
}
