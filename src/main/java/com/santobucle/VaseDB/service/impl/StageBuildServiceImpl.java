package com.santobucle.VaseDB.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.dto.StageBuildDto;
import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.entity.Build;
import com.santobucle.VaseDB.entity.Stage;
import com.santobucle.VaseDB.entity.StageBuild;
import com.santobucle.VaseDB.exception.ResourceNotFoundException;
import com.santobucle.VaseDB.mapper.BuildMapper;
import com.santobucle.VaseDB.mapper.StageBuildMapper;
import com.santobucle.VaseDB.mapper.StageMapper;
import com.santobucle.VaseDB.repository.StageBuildRepository;
import com.santobucle.VaseDB.service.BuildService;
import com.santobucle.VaseDB.service.StageBuildService;
import com.santobucle.VaseDB.service.StageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StageBuildServiceImpl implements StageBuildService {

    private StageBuildRepository stageBuildRepository;

    @Autowired
    private StageService stageService;

    @Autowired
    private BuildService buildService;

    public StageBuildDto createStageBuild(StageBuildDto stageBuildDto) {
        StageBuild stageBuild = StageBuildMapper.mapToStageBuild(stageBuildDto);
        
        StageDto stageDto = stageService.createStage(stageBuildDto.getStageDto()); 
        Stage stage = StageMapper.mapToStage(stageDto);
        stageBuild.setStage(stage);

        BuildDto buildDto = buildService.createBuild(stageBuildDto.getBuildDto());
        Build build = BuildMapper.mapToBuild(buildDto);
        stageBuild.setBuild(build);
        
        Optional<StageBuild> savedStageBuild = stageBuildRepository.saveWithJsonCast(
            stageBuild.getBuild().getId(),
            stageBuild.getStage().getId(),
            stageBuild.getDuration(),
            stageBuild.getQualifierData());
        
        return StageBuildMapper.mapToStageBuildDto(savedStageBuild.orElseThrow(() ->
            new IllegalStateException("StageBuild could not be saved successfully")));
    }

    public StageBuildDto getStageBuildById(Long stageBuildId) {
        StageBuild build = stageBuildRepository.findById(stageBuildId)
            .orElseThrow(() ->
                new ResourceNotFoundException("StageBuild with id " + stageBuildId + " does not exist."));
        return StageBuildMapper.mapToStageBuildDto(build);
    }

    public StageBuildDto getStageBuildByName(String stageBuildName) {
        StageBuild build = stageBuildRepository.findByName(stageBuildName)
            .orElseThrow(() ->
                new ResourceNotFoundException("StageBuild with id " + stageBuildName + " does not exist."));
        return StageBuildMapper.mapToStageBuildDto(build);
    }
}
