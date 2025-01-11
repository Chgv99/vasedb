package com.santobucle.VaseDB.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.StageBuildDto;
import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.entity.Stage;
import com.santobucle.VaseDB.entity.StageBuild;
import com.santobucle.VaseDB.exception.ResourceNotFoundException;
import com.santobucle.VaseDB.mapper.StageBuildMapper;
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
        StageDto existingStageDto = getStageByName(getFormattedName(stageDto.getName()));
        if (existingStageDto != null)
            return existingStageDto;

        Stage stage = StageMapper.mapToStage(stageDto);
        return createStage(stage);
    }
    
    public StageDto createStage(Stage stage) {
        Stage savedStage = stageRepository.save(stage);
        return StageMapper.mapToStageDto(savedStage);
    }

    @Override
    public StageDto addStageBuildToList(StageDto stageDto, StageBuildDto stageBuildDto) {
        Stage stage = StageMapper.mapToStage(stageDto);
        List<StageBuild> stageBuilds = stage.getStageBuilds();
        if (stageBuilds == null)
            stageBuilds = new ArrayList<StageBuild>();
        stageBuilds.add(StageBuildMapper.mapToStageBuild(stageBuildDto));
        stage.setStageBuilds(stageBuilds);
        return createStage(stage);
    }

    @Override
    public StageDto getStageById(Long stageId) {
        Stage stage = stageRepository.findById(stageId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Stage with id " + stageId + " does not exist."));
        return StageMapper.mapToStageDto(stage);
    }

    @Override
    public StageDto getStageByName(String stageName) {
        Stage stage = stageRepository.findByName(stageName)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Stage with name '" + stageName + "'' does not exist."));
        return StageMapper.mapToStageDto(stage);
    }

    private String getFormattedName(String name) {
        name = name.toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}
