package com.santobucle.VaseDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santobucle.VaseDB.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
