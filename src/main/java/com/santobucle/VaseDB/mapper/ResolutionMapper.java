package com.santobucle.VaseDB.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.dto.VaseAttributesDto;
import com.santobucle.VaseDB.entity.Resolution;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ResolutionMapper {

    public ResolutionDto mapToResolutionDto(Resolution resolution) {
        VaseAttributesDto vaseAttributesDto = new VaseAttributesDto();
        // Deserialization
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            vaseAttributesDto = objectMapper.readValue(resolution.getVaseAttributesDto(),
                    new TypeReference<VaseAttributesDto>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        Long gameId = null;
        if (resolution.getGame() != null) {
            gameId = resolution.getGame().getId();
        }

        return new ResolutionDto(
                resolution.getId(),
                gameId,
                resolution.getDecision(),
                resolution.getResult(),
                resolution.getElapsedTime(),
                resolution.getSpeedQualifier(),
                StageMapper.mapToStageDto(resolution.getStage()),
                vaseAttributesDto,
                resolution.getDate());
    }

    public Resolution mapToResolution(ResolutionDto resolutionDto) {
        String vaseAttributesJson = "";
        // Serialization
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            vaseAttributesJson = objectMapper.writeValueAsString(resolutionDto.getVaseAttributesDto());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Resolution(
                resolutionDto.getId(),
                null, // gameService.findGameById(resolutionDto.getGameId()),
                resolutionDto.getDecision(),
                resolutionDto.getResult(),
                resolutionDto.getElapsedTime(),
                resolutionDto.getSpeedQualifier(),
                StageMapper.mapToStage(resolutionDto.getStageDto()),
                vaseAttributesJson,
                resolutionDto.getDate());
    }
}
