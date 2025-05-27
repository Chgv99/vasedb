package com.santobucle.VaseDB.mapper;

import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.entity.Stage;

public class StageMapper {

    public static StageDto mapToStageDto(Stage stage) {
        return new StageDto(
                stage.getId(),
                stage.getName());
    }

    public static Stage mapToStage(StageDto stageDto) {
        return new Stage(
                stageDto.getId(),
                stageDto.getName());
    }
}
