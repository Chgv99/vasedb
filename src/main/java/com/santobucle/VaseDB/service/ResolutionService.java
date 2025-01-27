package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.entity.Resolution;

public interface ResolutionService {
    ResolutionDto createResolution(ResolutionDto resolutionDto);

    public ResolutionDto getResolutionById(Long resolutionId);

    public Resolution save(Resolution resolution);

    public Resolution saveWithJsonCast(Resolution resolution) throws IllegalStateException;
}
