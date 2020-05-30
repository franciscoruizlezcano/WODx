package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class TraininglevelDTO {

    private Integer idTrainingLevel;

    private String description;

    private CompanyDTO company;

    public TraininglevelDTO() {}

    public TraininglevelDTO(Integer idTrainingLevel) {
        this.idTrainingLevel = idTrainingLevel;
    }
}
