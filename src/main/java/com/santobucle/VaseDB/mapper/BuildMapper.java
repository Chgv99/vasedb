package com.santobucle.VaseDB.mapper;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.entity.Build;

public class BuildMapper {

    public static BuildDto mapToBuildDto(Build build) {
        return new BuildDto(
            build.getId(),
            build.getVersion()
        );
    }
    
    public static Build mapToBuild(BuildDto buildDto) {
        return new Build(
            buildDto.getId(),
            buildDto.getVersion(),
            null
        );
    }
}
