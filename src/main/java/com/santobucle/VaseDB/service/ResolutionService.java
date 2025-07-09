package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.ResolutionDto;

public interface ResolutionService {
    public ResolutionDto createResolution(ResolutionDto resolutionDto);

    public ResolutionDto getResolutionById(Long resolutionId);

    public ResolutionDto save(ResolutionDto resolution);

    public ResolutionDto saveWithJsonCast(ResolutionDto resolutionDto) throws IllegalStateException;
}
