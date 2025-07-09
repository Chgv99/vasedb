package com.santobucle.VaseDB.dto;

import java.util.Date;

import com.santobucle.VaseDB.dto.enums.Decision;
import com.santobucle.VaseDB.dto.enums.Result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResolutionDto {
    private Long id;
    // private Long gameId;
    private Decision decision;
    private Result result;
    private double elapsedTime;
    private String speedQualifier;
    private StageDto stageDto;
    private VaseAttributesDto vaseAttributesDto;
    private Date date;
}
