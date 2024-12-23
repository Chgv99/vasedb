package com.santobucle.VaseDB.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santobucle.VaseDB.dto.StageDto;
import com.santobucle.VaseDB.service.StageService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("/api/stages")
public class StageController {

    private StageService stageService;
    
    @PostMapping
    public ResponseEntity<StageDto> createStage(@RequestBody StageDto stageDto) {
        StageDto savedStage = stageService.createStage(stageDto);
        return new ResponseEntity<>(savedStage, HttpStatus.CREATED);
    }
}
