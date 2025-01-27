package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.StageBuildDto;
import com.santobucle.VaseDB.entity.StageBuild;

public interface StageBuildService {
    StageBuildDto createStageBuild(StageBuild stageBuild);

    public StageBuildDto getStageBuildById(Long stageBuildId);

    public StageBuild getStageBuild(StageBuild stageBuild);

    public StageBuild resolveStageBuild(StageBuild stageBuild);

    public StageBuild saveWithJsonCast(StageBuild stageBuild);
}
