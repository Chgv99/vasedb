package com.santobucle.VaseDB.dto.response;

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
public class GameResponse {
    private Long id;
    private UserResponse user;
    private float totalTime;
    private int score;
    private boolean isHiScore;
    private String build;
    private List<ResolutionDto> resolutions;
    private Date date;
}
