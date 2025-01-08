package com.santobucle.VaseDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.service.StageService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stages")
public class StageController {

    @Autowired
    private StageService stageService;
    
    @GetMapping("{stageId}")
    public ResponseEntity<StageDto> getStageById(@PathVariable Long stageId) {
        StageDto stageDto = stageService.getStageById(stageId);
        return ResponseEntity.ok(stageDto);
    }

    @GetMapping("{stageName}")
    public ResponseEntity<StageDto> getStageById(@PathVariable String stageName) {
        StageDto stageDto = stageService.getStageByName(stageName);
        return ResponseEntity.ok(stageDto);
    }

    @PostMapping
    public ResponseEntity<StageDto> createStage(@RequestBody StageDto stageDto) {
        StageDto savedStage = stageService.createStage(stageDto);
        return new ResponseEntity<>(savedStage, HttpStatus.CREATED);
    }
}
