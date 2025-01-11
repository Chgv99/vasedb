package com.santobucle.VaseDB.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.dto.StageBuildDto;
import com.santobucle.VaseDB.entity.Build;
import com.santobucle.VaseDB.entity.StageBuild;
import com.santobucle.VaseDB.exception.ResourceNotFoundException;
import com.santobucle.VaseDB.mapper.BuildMapper;
import com.santobucle.VaseDB.mapper.StageBuildMapper;
import com.santobucle.VaseDB.repository.BuildRepository;
import com.santobucle.VaseDB.service.BuildService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuildServiceImpl implements BuildService {

    private BuildRepository buildRepository;

    @Override
    public BuildDto createBuild(BuildDto buildDto) {
        BuildDto resultBuildDto;
        try {
            resultBuildDto = getBuildByName(buildDto.getVersion());
        } catch (ResourceNotFoundException e) {
            resultBuildDto = createBuild(BuildMapper.mapToBuild(buildDto));
        }
        return resultBuildDto;
    }

    @Override
    public BuildDto createBuild(Build build) {
        Build savedBuild = buildRepository.save(build);
        return BuildMapper.mapToBuildDto(savedBuild);
    }

    @Override
    public BuildDto addStageBuildToList(BuildDto buildDto, StageBuildDto stageBuildDto) {
        Build build = BuildMapper.mapToBuild(buildDto);
        List<StageBuild> stageBuilds = build.getStageBuilds();
        if (stageBuilds == null)
            stageBuilds = new ArrayList<StageBuild>();
        stageBuilds.add(StageBuildMapper.mapToStageBuild(stageBuildDto));
        build.setStageBuilds(stageBuilds);
        return createBuild(build);
    }

    @Override
    public BuildDto getBuildById(Long buildId) {
        Build build = buildRepository.findById(buildId)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Build with id " + buildId + " does not exist."));
        return BuildMapper.mapToBuildDto(build);
    }

    @Override
    public BuildDto getBuildByName(String buildName) {
        Build build = buildRepository.findByName(buildName)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Build with name " + buildName + " does not exist."));
        return BuildMapper.mapToBuildDto(build);
    }
}
