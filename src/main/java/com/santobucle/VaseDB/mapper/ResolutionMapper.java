package com.santobucle.VaseDB.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.dto.VaseAttributesDto;
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
        VaseAttributesDto vaseAttributesDto = new VaseAttributesDto();
        // Deserialization
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            vaseAttributesDto = objectMapper.readValue(resolution.getVaseAttributesDto(), new TypeReference<VaseAttributesDto>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResolutionDto(
            resolution.getId(),
            StageBuildMapper.mapToStageBuildDto(resolution.getStageBuild()),
            resolution.getElapsedTime(),
            resolution.getSpeedQualifier(),
            vaseAttributesDto,
            resolution.getDate()
        );
    }

    public static Resolution mapToResolution(ResolutionDto resolutionDto) {
        String vaseAttributesJson = "";
        //Serialization
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            vaseAttributesJson = objectMapper.writeValueAsString(resolutionDto.getVaseAttributesDto());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Resolution(
            resolutionDto.getId(), 
            StageBuildMapper.mapToStageBuild(resolutionDto.getStageBuildDto()),
            resolutionDto.getElapsedTime(),
            resolutionDto.getSpeedQualifier(),
            vaseAttributesJson,
            resolutionDto.getDate() != null ? resolutionDto.getDate() : new Date()
        );
    }
}
