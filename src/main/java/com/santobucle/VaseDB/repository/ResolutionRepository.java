package com.santobucle.VaseDB.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.santobucle.VaseDB.dto.enums.Decision;
import com.santobucle.VaseDB.dto.enums.Result;
import com.santobucle.VaseDB.entity.Resolution;

public interface ResolutionRepository extends JpaRepository<Resolution, Long> {
        @Query(value = "INSERT INTO resolution (game_id, decision, result, elapsed_time, speed_qualifier, stage_id, vase_attributes, solved_at) " +
                        "VALUES (:gameId, CAST(:decision AS decision), CAST(:result AS result), :elapsedTime, :speedQualifier, :stage_id, cast(:vaseAttributes as json), :date)" +
                        "RETURNING *", nativeQuery = true)
        Optional<Resolution> saveWithJsonCast(@Param("gameId") Long gameId,
                        @Param("decision") String decision,
                        @Param("result") String result,
                        @Param("elapsedTime") double elapsedTime,
                        @Param("speedQualifier") String speedQualifier,
                        @Param("stage_id") Long stageId,
                        @Param("vaseAttributes") String vaseAttributeString,
                        @Param("date") Date date);
}
