package com.santobucle.VaseDB.service.impl;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.entity.Resolution;
import com.santobucle.VaseDB.exception.ResourceNotFoundException;
import com.santobucle.VaseDB.mapper.ResolutionMapper;
import com.santobucle.VaseDB.repository.ResolutionRepository;
import com.santobucle.VaseDB.service.ResolutionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    private ResolutionRepository resolutionRepository;

    private ResolutionMapper resolutionMapper;

    @Override
    public ResolutionDto createResolution(ResolutionDto resolutionDto) {
        Resolution resolution = resolutionMapper.mapToResolution(resolutionDto);

        Resolution savedResolution = saveWithJsonCast(resolution);
        return resolutionMapper.mapToResolutionDto(savedResolution);
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
                resolution.getElapsedTime(),
                resolution.getSpeedQualifier(),
                resolution.getVaseAttributesDto(),
                resolution.getDate())
                .orElseThrow(() -> new IllegalStateException("An error has ocurred when trying to save Resolution."));
        // return resolutionMapper.mapToResolutionDto(savedResolution);
        return savedResolution;
    }

}
