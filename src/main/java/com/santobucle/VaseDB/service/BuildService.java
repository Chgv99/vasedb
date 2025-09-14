package com.santobucle.VaseDB.service;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.entity.Build;

public interface BuildService {
    public BuildDto createBuild(String name);

    public BuildDto getBuildById(Long buildId);

    public BuildDto getBuildByName(String buildName);

    public Build getBuild(Build build);

    public BuildDto getLatestBuild();

    public Build resolveBuild(Build build);
}
