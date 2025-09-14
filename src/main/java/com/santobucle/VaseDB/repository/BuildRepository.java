package com.santobucle.VaseDB.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.santobucle.VaseDB.entity.Build;

public interface BuildRepository extends JpaRepository<Build, Long> {

    @Query(value = "SELECT * FROM Build WHERE name = ?1", nativeQuery = true)
    Optional<Build> findByName(String name);

    Optional<Build> findTop1ByOrderByIdDesc();
}
