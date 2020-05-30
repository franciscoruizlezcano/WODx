package com.ls.wod.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author francisco
 */

@Data
public class CountryDTO implements Serializable {

    private Integer idCountry;

    private String description;

    private String phonecode;
    

    public CountryDTO() {}

    public CountryDTO(Integer idCountry) {
        this.idCountry = idCountry;
    }
}
