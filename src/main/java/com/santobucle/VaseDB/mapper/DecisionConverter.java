package com.santobucle.VaseDB.mapper;

import com.santobucle.VaseDB.dto.enums.Decision;

import jakarta.persistence.AttributeConverter;

public class DecisionConverter implements AttributeConverter<Decision, String> {

    @Override
    public String convertToDatabaseColumn(Decision attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public Decision convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Decision.valueOf(dbData);
    }
}