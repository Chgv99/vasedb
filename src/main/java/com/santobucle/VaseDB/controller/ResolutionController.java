package com.santobucle.VaseDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.service.ResolutionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/resolutions")
public class ResolutionController {

    @Autowired
    private ResolutionService resolutionService;

    @PostMapping
    public ResponseEntity<ResolutionDto> createResolution(@RequestBody ResolutionDto resolutionDto) {
        ResolutionDto savedResolution = resolutionService.createResolution(resolutionDto);
        return new ResponseEntity<>(savedResolution, HttpStatus.CREATED);
    }

}
