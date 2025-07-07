package com.santobucle.VaseDB.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santobucle.VaseDB.dto.ResolutionDto;
import com.santobucle.VaseDB.dto.VaseAttributesDto;
import com.santobucle.VaseDB.dto.enums.Decision;
import com.santobucle.VaseDB.dto.enums.Result;
import com.santobucle.VaseDB.entity.Resolution;

@Component
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

        return new ResolutionDto(
                resolution.getId(),
                resolution.getDecision(),
                resolution.getResult(),
                resolution.getElapsedTime(),
                resolution.getSpeedQualifier(),
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
                vaseAttributesJson,
                resolutionDto.getDate());
    }
}
