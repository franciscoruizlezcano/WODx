package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class CompanyDTO{

    private Integer idCompany;

    private String description;

    public CompanyDTO() {}

    public CompanyDTO(Integer idCompany) {
        this.idCompany = idCompany;
    }
}
