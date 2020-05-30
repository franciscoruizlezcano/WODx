package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class AthleteDTO{

    private Integer idAthlete;

    private UserDTO user;

    private TraininglevelDTO traininglevel;

    public AthleteDTO() {}

    public AthleteDTO(Integer idAthlete) {
        this.idAthlete = idAthlete;
    }
}
