package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.StageBuildDto;

public interface StageBuildService {
    StageBuildDto createStageBuild(StageBuildDto stageBuildDto);

    public StageBuildDto getStageBuildById(Long stageBuildId);

    public StageBuildDto getStageBuildByName(String stageBuildName);
}
