package com.santobucle.VaseDB.service.impl;

import org.springframework.stereotype.Service;

import com.santobucle.VaseDB.dto.BuildDto;
import com.santobucle.VaseDB.entity.Build;
import com.santobucle.VaseDB.exception.ResourceNotFoundException;
import com.santobucle.VaseDB.mapper.BuildMapper;
import com.santobucle.VaseDB.repository.BuildRepository;
import com.santobucle.VaseDB.service.BuildService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuildServiceImpl implements BuildService {

    private BuildRepository buildRepository;

    @Override
    public BuildDto createBuild(String name) {
        BuildDto savedBuildDto;
        try {
            savedBuildDto = getBuildByName(name);
        } catch (ResourceNotFoundException e) {
            Build savedBuild = buildRepository.save(new Build(name));
            savedBuildDto = BuildMapper.mapToBuildDto(savedBuild);
        }
        return savedBuildDto;
    }

    @Override
    public BuildDto getBuildById(Long buildId) {
        Build build = buildRepository.findById(buildId)
                .orElseThrow(() -> new ResourceNotFoundException("Build with id " + buildId + " does not exist."));
        return BuildMapper.mapToBuildDto(build);
    }

    @Override
    public BuildDto getBuildByName(String buildName) {
        Build build = buildRepository.findByName(buildName)
                .orElseThrow(() -> new ResourceNotFoundException("Build with name " + buildName + " does not exist."));
        return BuildMapper.mapToBuildDto(build);
    }

    @Override
    public Build getBuild(Build build) {
        return buildRepository.findByName(build.getVersion()).orElse(null);
    }

    @Override
    public Build resolveBuild(Build build) {
        return buildRepository.findByName(build.getVersion())
                .orElseGet(() -> buildRepository.save(build));
    }
}
