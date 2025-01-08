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
public class BuildDto {
    private Long id;
    private String version;
    private Date releaseDate;
    // private List<StageBuild> stageBuilds;
}
