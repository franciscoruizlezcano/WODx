package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class WeightunitDTO {

    private Short idWeightUnit;

    private String description;

    public WeightunitDTO() {}

    public WeightunitDTO(Short idWeightUnit) {
        this.idWeightUnit = idWeightUnit;
    }
}
