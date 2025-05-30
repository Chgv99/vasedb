package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.entity.Stage;

public interface StageService {
    public StageDto createStage(StageDto stageDto);

    public StageDto createStage(Stage stage);

    public StageDto getStageById(Long stageId);

    public StageDto getStageByName(String stageName);

    public Stage getStage(Stage stage);

    public Stage resolveStage(Stage stage);
}
