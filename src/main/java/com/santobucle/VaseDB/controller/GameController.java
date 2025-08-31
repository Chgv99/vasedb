package com.santobucle.VaseDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santobucle.VaseDB.dto.request.GameRequest;
import com.santobucle.VaseDB.dto.response.GameResponse;
import com.santobucle.VaseDB.mapper.GameMapper;
import com.santobucle.VaseDB.service.GameService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMapper gameMapper;

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@RequestBody GameRequest gameRequest) {
        try {
            return new ResponseEntity<>(gameService.createGame(gameRequest), HttpStatus.CREATED);
        } catch (NullPointerException e) {
            System.err.println("An error ocurred while saving a game. " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
