package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class TimeunitDTO {

    private Integer idTimeUnit;

    private String description;

    public TimeunitDTO() {}

    public TimeunitDTO(Integer idTimeUnit) {
        this.idTimeUnit = idTimeUnit;
    }

    public TimeunitDTO(Integer idTimeUnit, String description) {
        this.idTimeUnit = idTimeUnit;
        this.description = description;
    }
    
}
