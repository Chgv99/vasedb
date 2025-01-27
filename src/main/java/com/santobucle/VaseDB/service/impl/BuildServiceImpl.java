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
