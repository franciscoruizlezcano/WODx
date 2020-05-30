package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class PhoneDTO{

    private Integer idPhone;

    private String phone;

    private CountryDTO country;

    public PhoneDTO() {}

    public PhoneDTO(Integer idPhone) {
        this.idPhone = idPhone;
    }
}
