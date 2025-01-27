package com.santobucle.VaseDB.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santobucle.VaseDB.dto.GameDto;
import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.service.GameService;
import com.santobucle.VaseDB.service.ResolutionService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("/api/game")
public class GameController {
    
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<GameDto> createResolution(@RequestBody GameDto gameDto) {
        try {
            GameDto savedGame = gameService.createGame(gameDto);
            return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
        } catch (NullPointerException e) {
            System.err.println("An error ocurred while saving a game. " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
