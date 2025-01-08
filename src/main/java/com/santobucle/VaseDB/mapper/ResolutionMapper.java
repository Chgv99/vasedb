package com.santobucle.VaseDB.mapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.entity.Resolution;
import com.santobucle.VaseDB.entity.StageBuild;
import com.santobucle.VaseDB.service.BuildService;
import com.santobucle.VaseDB.service.StageService;

public class ResolutionMapper {

    @Autowired
    static StageService stageService;

    @Autowired
    static BuildService buildService;

    public static ResolutionDto mapToResolutionDto(Resolution resolution) {
        return new ResolutionDto(
            resolution.getId(),
            StageBuildMapper.mapToStageBuildDto(resolution.getStageBuild()),
            resolution.getElapsedTime(),
            resolution.getSpeedQualifier(),
            resolution.getDate()
        );
    }

    public static Resolution mapToResolution(ResolutionDto resolutionDto) {
        return new Resolution(
            resolutionDto.getId(), 
            StageBuildMapper.mapToStageBuild(resolutionDto.getStageBuildDto()),
            resolutionDto.getElapsedTime(),
            resolutionDto.getSpeedQualifier(),
            resolutionDto.getDate() != null ? resolutionDto.getDate() : new Date()
        );
    }
}
