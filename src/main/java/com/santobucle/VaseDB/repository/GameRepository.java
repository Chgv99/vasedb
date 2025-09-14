package com.santobucle.VaseDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.santobucle.VaseDB.entity.Build;
import com.santobucle.VaseDB.entity.Game;
import com.santobucle.VaseDB.entity.User;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = """
            SELECT *
            FROM (
                SELECT
                    g.*, u.*,
                    ROW_NUMBER() OVER (
                        PARTITION BY g.user_uuid
                        ORDER BY g.score DESC, g.played_at ASC
                    ) AS rn
                FROM game g
                LEFT JOIN users u ON g.user_uuid = u.uuid
                WHERE g.build_id = :buildId
                  AND u.nickname IS NOT NULL
            ) sub
            WHERE rn = 1
            ORDER BY score DESC, played_at ASC
            LIMIT 10
            """, nativeQuery = true)
    List<Game> findTop10GamesUniqueUserByBuild(@Param("buildId") Long buildId);
    
    @Query(value = """
            SELECT *
            FROM (
                SELECT
                    g.*, u.*,
                    ROW_NUMBER() OVER (
                        PARTITION BY g.user_uuid
                        ORDER BY g.score DESC, g.played_at ASC
                    ) AS rn
                FROM game g
                LEFT JOIN users u ON g.user_uuid = u.uuid
                WHERE g.build_id = :buildId
                  AND (u.nickname IS NOT NULL OR g.id = :gameId)
            ) sub
            WHERE rn = 1
            ORDER BY score DESC, played_at ASC
            LIMIT 10
            """, nativeQuery = true)
    List<Game> findTop10GamesUniqueUserByBuildIncludingGameId(@Param("gameId") Long gameId, @Param("buildId") Long buildId);

    List<Game> findByUserAndBuildOrderByScoreDescDateAsc(User user, Build build);
}
