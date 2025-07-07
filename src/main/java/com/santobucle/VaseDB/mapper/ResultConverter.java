package com.santobucle.VaseDB.mapper;

import com.santobucle.VaseDB.dto.enums.Result;

import jakarta.persistence.AttributeConverter;

public class ResultConverter implements AttributeConverter<Result, String> {

    @Override
    public String convertToDatabaseColumn(Result attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public Result convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Result.valueOf(dbData);
    }
}