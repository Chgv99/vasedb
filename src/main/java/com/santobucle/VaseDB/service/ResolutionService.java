package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.ResolutionDto;

public interface ResolutionService {
    ResolutionDto createResolution(ResolutionDto resolutionDto);

    public ResolutionDto getResolutionById(Long resolutionId);
}
