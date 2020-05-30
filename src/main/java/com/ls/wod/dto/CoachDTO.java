package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class CoachDTO{

    private Integer idCoach;

    private UserDTO user;
    
    public CoachDTO() {}

    public CoachDTO(Integer idCoach) {
        this.idCoach = idCoach;
    }

}
