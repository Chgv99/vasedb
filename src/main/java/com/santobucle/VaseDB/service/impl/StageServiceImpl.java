package com.santobucle.VaseDB.service.impl;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.entity.Stage;
import com.santobucle.VaseDB.mapper.StageMapper;
import com.santobucle.VaseDB.repository.StageRepository;
import com.santobucle.VaseDB.service.StageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StageServiceImpl implements StageService {

    private StageRepository stageRepository;

    @Override
    public StageDto createStage(StageDto stageDto) {
        Stage stage = StageMapper.mapToStage(stageDto);
        Stage savedStage = stageRepository.save(stage);
        return StageMapper.mapToStageDto(savedStage);
    }

}
