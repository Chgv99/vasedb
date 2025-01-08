package com.santobucle.VaseDB.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StageBuildDto {
    private Long id;
    private StageDto stageDto;
    private BuildDto buildDto;
    private int duration;
    private List<QualifierDataDto> qualifierData;
}
