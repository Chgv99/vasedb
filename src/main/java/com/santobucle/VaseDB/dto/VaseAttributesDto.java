package com.santobucle.VaseDB.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaseAttributesDto {
    private String size;
    private int handles;
    private String symmetry;
    private String topColor;
    private String bottomColor;
    private int decorativeStickers;
    private boolean officialSticker;
    private boolean fragileSticker;
    private boolean colorSticker;
    private String pitch;
}
