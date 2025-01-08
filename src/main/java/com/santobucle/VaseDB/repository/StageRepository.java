package com.santobucle.VaseDB.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.santobucle.VaseDB.entity.Stage;

public interface StageRepository extends JpaRepository<Stage, Long> {

    @Query(value = "SELECT * FROM Stage WHERE name = ?1", nativeQuery = true)
    Optional<Stage> findByName(String name);
}
