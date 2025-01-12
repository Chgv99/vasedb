package com.santobucle.VaseDB.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.santobucle.VaseDB.entity.Resolution;

public interface ResolutionRepository extends JpaRepository<Resolution, Long> {
    @Query(value = "INSERT INTO resolution (stage_build_id, elapsed_time, speed_qualifier, vase_attributes, solved_at) " +
            "VALUES (:stageBuildId, :elapsedTime, :speedQualifier, cast(:vaseAttributes as json), :date)" +
            "RETURNING *", nativeQuery = true)
    Optional<Resolution> saveWithJsonCast(@Param("stageBuildId") Long stageBuildId,
            @Param("elapsedTime") double elapsedTime,
            @Param("speedQualifier") String speedQualifier,
            @Param("vaseAttributes") String vaseAttributeString,
            @Param("date") Date date);
}
