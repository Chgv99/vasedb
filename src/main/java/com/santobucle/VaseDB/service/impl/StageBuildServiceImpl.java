package com.santobucle.VaseDB.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.StageBuildDto;
import com.santobucle.VaseDB.entity.Build;
import com.santobucle.VaseDB.entity.Stage;
import com.santobucle.VaseDB.entity.StageBuild;
import com.santobucle.VaseDB.exception.ResourceNotFoundException;
import com.santobucle.VaseDB.mapper.StageBuildMapper;
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

    @Override
    public StageBuildDto createStageBuild(StageBuild stageBuild) {

        Stage stage = stageService.resolveStage(stageBuild.getStage());
        stageBuild.setStage(stage);

        Build build = buildService.resolveBuild(stageBuild.getBuild());
        stageBuild.setBuild(build);

        Optional<StageBuild> savedStageBuild = stageBuildRepository.saveWithJsonCast(
                stageBuild.getBuild().getId(),
                stageBuild.getStage().getId(),
                stageBuild.getDuration(),
                stageBuild.getQualifierData());

        return StageBuildMapper.mapToStageBuildDto(savedStageBuild
                .orElseThrow(() -> new IllegalStateException("StageBuild could not be saved successfully")));
    }

    @Override
    public StageBuildDto getStageBuildById(Long stageBuildId) {
        StageBuild stageBuild = stageBuildRepository.findById(stageBuildId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("StageBuild with id " + stageBuildId + " does not exist."));
        return StageBuildMapper.mapToStageBuildDto(stageBuild);
    }

    // @Override
    // public StageBuildDto getStageBuildByStageAndBuildId(Long stageId, Long buildId) {
    //     StageBuild stageBuild = stageBuildRepository.findByStageAndBuildId(stageId, buildId)
    //             .orElseThrow(() -> new ResourceNotFoundException("StageBuild does not exist."));
    //     return StageBuildMapper.mapToStageBuildDto(stageBuild);
    // }

    @Override
    public StageBuild getStageBuild(StageBuild stageBuild) {
        return stageBuildRepository.findByStageAndBuildName(stageBuild.getStage().getName(),
                stageBuild.getBuild().getVersion()).orElse(null);
    }

    @Override
    public StageBuild resolveStageBuild(StageBuild stageBuild) {
        return stageBuildRepository.findByStageAndBuildName(
                stageBuild.getStage().getName(), stageBuild.getBuild().getVersion())
                .orElseGet(() -> {
                    return StageBuildMapper.mapToStageBuild(createStageBuild(stageBuild));
                });
    }

    @Override
    public StageBuild saveWithJsonCast(StageBuild stageBuild) {
        return stageBuildRepository.saveWithJsonCast(
            stageBuild.getBuild().getId(),
            stageBuild.getStage().getId(),
            stageBuild.getDuration(),
            stageBuild.getQualifierData()
        ).orElseThrow(() ->
            new IllegalStateException("An error has ocurred when trying to save StageBuild."));
    }
}
