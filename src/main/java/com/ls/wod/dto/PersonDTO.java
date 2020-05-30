package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class PersonDTO {

    private Integer idPerson;

    private String name;

    private String lastname;

    private String email;

    private PhoneDTO phone;
    

    public PersonDTO() {}

    public PersonDTO(Integer idPerson) {
        this.idPerson = idPerson;
    }
}
