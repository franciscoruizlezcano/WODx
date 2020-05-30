package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class UserDTO{

    private Integer idUser;

    private String username;

    private boolean status;

    private CompanyDTO company;

    private PersonDTO person;

    public UserDTO() {
    }

    public UserDTO(Integer idUser) {
        this.idUser = idUser;
    }
}
