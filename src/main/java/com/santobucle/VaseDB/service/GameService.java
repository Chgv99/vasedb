package com.santobucle.VaseDB.service;

import java.util.List;

import com.santobucle.VaseDB.dto.request.GameRequest;
import com.santobucle.VaseDB.dto.request.PersonalRankingRequest;
import com.santobucle.VaseDB.dto.request.RankingRequest;
import com.santobucle.VaseDB.dto.response.GameResponse;
import com.santobucle.VaseDB.dto.response.PersonalRankingResponse;
import com.santobucle.VaseDB.dto.response.RankingResponse;
import com.santobucle.VaseDB.dto.response.ReducedGameResponse;

public interface GameService {
    public GameResponse createGame(GameRequest gameDto);

    public RankingResponse getTop10Ranking(RankingRequest rankingRequest);

    public PersonalRankingResponse getPersonalRanking(PersonalRankingRequest rankingRequest);

    public List<ReducedGameResponse> getLeaderboard(String buildName);
}
