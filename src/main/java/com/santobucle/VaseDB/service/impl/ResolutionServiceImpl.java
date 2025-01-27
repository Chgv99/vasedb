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

    private ResolutionMapper resolutionMapper;

    @Override
    public ResolutionDto createResolution(ResolutionDto resolutionDto) {
        Resolution resolution = resolutionMapper.mapToResolution(resolutionDto);
        StageBuild savedStageBuild = stageBuildService
                .resolveStageBuild(resolution.getStageBuild());
        resolution.setStageBuild(savedStageBuild);
        Optional<Resolution> savedResolution = resolutionRepository.saveWithJsonCast(
            resolution.getGame() == null ? null : resolution.getGame().getId(),
            resolution.getStageBuild().getId(),
            resolution.getElapsedTime(),
            resolution.getSpeedQualifier(),
            resolution.getVaseAttributesDto(),
            resolution.getDate());
        return resolutionMapper.mapToResolutionDto(savedResolution.orElseThrow(() -> 
            new IllegalStateException("Resolution could not be saved successfully")));
    }

    @Override
    public ResolutionDto getResolutionById(Long resolutionId) {
        Resolution resolution = resolutionRepository.findById(resolutionId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resolution with id " + resolutionId + " does not exist."));
        return resolutionMapper.mapToResolutionDto(resolution);
    }
    
    @Override
    public Resolution save(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }
    
    @Override
    public Resolution saveWithJsonCast(Resolution resolution) throws IllegalStateException {
        Resolution savedResolution = resolutionRepository.saveWithJsonCast(
            resolution.getGame().getId(),
            resolution.getStageBuild().getId(),
            resolution.getElapsedTime(),
            resolution.getSpeedQualifier(),
            resolution.getVaseAttributesDto(),
            resolution.getDate()).orElseThrow(() ->
                new IllegalStateException("An error has ocurred when trying to save Resolution."));
                // return resolutionMapper.mapToResolutionDto(savedResolution);
                return savedResolution;
    }

}
