package com.santobucle.VaseDB.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StageDto {
    private Long id;
    private String name;

    public StageDto(String name) {
        this.name = name;
    }
}
