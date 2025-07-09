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
        return saveWithJsonCast(resolutionDto);
    }

    @Override
    public ResolutionDto getResolutionById(Long resolutionId) {
        Resolution resolution = resolutionRepository.findById(resolutionId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resolution with id " + resolutionId + " does not exist."));
        return resolutionMapper.mapToResolutionDto(resolution);
    }

    @Override
    public ResolutionDto save(ResolutionDto resolutionDto) {
        Resolution resolution = resolutionRepository.save(resolutionMapper.mapToResolution(resolutionDto));
        return resolutionMapper.mapToResolutionDto(resolution);
    }

    @Override
    public ResolutionDto saveWithJsonCast(ResolutionDto resolutionDto) throws IllegalStateException {
        Resolution savedResolution = resolutionRepository.saveWithJsonCast(
                resolutionDto.getGameId(),
                resolutionDto.getDecision().name(),
                resolutionDto.getResult().name(),
                resolutionDto.getElapsedTime(),
                resolutionDto.getSpeedQualifier(),
                resolutionDto.getStageDto().getId(),
                null,// resolutionDto.getVaseAttributesDto().toString(),
                resolutionDto.getDate())
                .orElseThrow(() -> new IllegalStateException("An error has ocurred when trying to save Resolution."));
        return resolutionMapper.mapToResolutionDto(savedResolution);
    }

}
