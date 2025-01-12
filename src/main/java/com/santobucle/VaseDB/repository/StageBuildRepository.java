package com.santobucle.VaseDB.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.santobucle.VaseDB.entity.StageBuild;

public interface StageBuildRepository extends JpaRepository<StageBuild, Long> {

    @Query(value = "SELECT * FROM stage_build WHERE name = ?1", nativeQuery = true)
    Optional<StageBuild> findByName(String name);

    @Query(value = "INSERT INTO stage_build (build_id, stage_id, duration, qualifier_data) " +
                   "VALUES (:buildId, :stageId, :duration, cast(:qualifierData as json))" +
                   "RETURNING *", 
           nativeQuery = true)
    Optional<StageBuild> saveWithJsonCast(@Param("buildId") Long buildId, 
                        @Param("stageId") Long stageId,
                        @Param("duration") int duration, 
            @Param("qualifierData") String qualifierData);
                        
    @Query(value = "SELECT * FROM stage_build WHERE stage_id = ?1 and build_id = ?2", nativeQuery = true)
    Optional<StageBuild> findByStageAndBuildId(@Param("stageId") Long stageId, @Param("buildId") Long buildId);
}
