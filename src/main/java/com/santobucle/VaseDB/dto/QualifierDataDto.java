package com.santobucle.VaseDB.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualifierDataDto {
    private String name;
    private double maximumElapsedTime;
    private double timeBonus;
}
