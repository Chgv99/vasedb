package com.santobucle.VaseDB.dto;

import java.util.Date;

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
    private StageBuildDto stageBuildDto;
    private double elapsedTime;
    private String speedQualifier;
    private VaseAttributesDto vaseAttributesDto;
    private Date date;
}
