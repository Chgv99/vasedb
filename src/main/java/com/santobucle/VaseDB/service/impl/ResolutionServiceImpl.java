package com.santobucle.VaseDB.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.dto.StageBuildDto;
import com.santobucle.VaseDB.entity.Resolution;
import com.santobucle.VaseDB.entity.StageBuild;
import com.santobucle.VaseDB.exception.ResourceNotFoundException;
import com.santobucle.VaseDB.mapper.ResolutionMapper;
import com.santobucle.VaseDB.mapper.StageBuildMapper;
import com.santobucle.VaseDB.repository.ResolutionRepository;
import com.santobucle.VaseDB.service.ResolutionService;
import com.santobucle.VaseDB.service.StageBuildService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    private ResolutionRepository resolutionRepository;

    private StageBuildService stageBuildService;

    @Override
    public ResolutionDto createResolution(ResolutionDto resolutionDto) {
        Resolution resolution = ResolutionMapper.mapToResolution(resolutionDto);
        StageBuildDto savedStageBuildDto = stageBuildService
                .createStageBuild(StageBuildMapper.mapToStageBuildDto(resolution.getStageBuild()));
        resolution.setStageBuild(StageBuildMapper.mapToStageBuild(savedStageBuildDto));
        Optional<Resolution> savedResolution = resolutionRepository.saveWithJsonCast(
            resolution.getStageBuild().getId(),
            resolution.getElapsedTime(),
            resolution.getSpeedQualifier(),
            resolution.getVaseAttributes(),
            resolution.getDate());
        return ResolutionMapper.mapToResolutionDto(savedResolution.orElseThrow(() -> 
            new IllegalStateException("Resolution could not be saved successfully")));
    }

    @Override
    public ResolutionDto getResolutionById(Long resolutionId) {
        Resolution resolution = resolutionRepository.findById(resolutionId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Resolution with id " + resolutionId + " does not exist."));
        return ResolutionMapper.mapToResolutionDto(resolution);
    }

}
