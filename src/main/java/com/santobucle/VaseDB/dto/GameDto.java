package com.santobucle.VaseDB.dto;

import java.util.Date;
import java.util.List;

import com.santobucle.VaseDB.entity.Resolution;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;
    private float totalTime;
    private List<ResolutionDto> resolutions;
    private Date date;
}
