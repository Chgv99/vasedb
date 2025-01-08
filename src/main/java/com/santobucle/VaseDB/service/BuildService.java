package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.dto.StageBuildDto;
import com.santobucle.VaseDB.entity.Build;

public interface BuildService {
    public BuildDto createBuild(BuildDto buildDto);

    public BuildDto createBuild(Build build);

    public BuildDto addStageBuildToList(BuildDto buildDto, StageBuildDto stageBuildDto);

    public BuildDto getBuildById(Long buildId);

    public BuildDto getBuildByName(String buildName);
}
