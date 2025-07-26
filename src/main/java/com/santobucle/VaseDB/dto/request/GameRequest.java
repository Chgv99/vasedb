package com.santobucle.VaseDB.dto.request;

import java.util.Date;
import java.util.List;

import com.santobucle.VaseDB.dto.ResolutionDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameRequest {
    private Long id;
    private float totalTime;
    private String build;
    private List<ResolutionDto> resolutions;
    private Date date;
}
