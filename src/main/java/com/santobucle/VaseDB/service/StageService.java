package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.StageBuildDto;
import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.entity.Stage;

public interface StageService {
    public StageDto createStage(StageDto stageDto);

    public StageDto createStage(Stage stage);

    public StageDto addStageBuildToList(StageDto stageDto, StageBuildDto stageBuildDto);

    public StageDto getStageById(Long stageId);

    public StageDto getStageByName(String stageName);
}
