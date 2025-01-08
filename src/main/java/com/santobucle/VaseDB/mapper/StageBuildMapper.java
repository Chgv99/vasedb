package com.santobucle.VaseDB.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santobucle.VaseDB.dto.QualifierDataDto;
import com.santobucle.VaseDB.dto.StageBuildDto;
import com.santobucle.VaseDB.entity.Resolution;
import com.santobucle.VaseDB.entity.StageBuild;

public class StageBuildMapper {

    public static StageBuildDto mapToStageBuildDto(StageBuild stageBuild) {        
        List<QualifierDataDto> qualifierDataDtoList = new ArrayList<QualifierDataDto>();
        // Deserialization
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            qualifierDataDtoList = objectMapper.readValue(stageBuild.getQualifierData(), new TypeReference<List<QualifierDataDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StageBuildDto(
            stageBuild.getId(),
            StageMapper.mapToStageDto(stageBuild.getStage()),
            BuildMapper.mapToBuildDto(stageBuild.getBuild()),
            stageBuild.getDuration(),
            qualifierDataDtoList
        );
    }
    
    public static StageBuild mapToStageBuild(StageBuildDto stageBuildDto) {
        String json = "";
        // Serialization
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(stageBuildDto.getQualifierData());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StageBuild(
            stageBuildDto.getId(),
            StageMapper.mapToStage(stageBuildDto.getStageDto()),
            BuildMapper.mapToBuild(stageBuildDto.getBuildDto()),
            stageBuildDto.getDuration(),
            json,
            new ArrayList<Resolution>()
        );
    }
}
