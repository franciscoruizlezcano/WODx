package com.ls.wod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author francisco
 */

@Entity
@Table(name = "country")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCountry")
    private Integer idCountry;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 3)
    @Column(name = "iso3")
    private String iso3;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 2)
    @Column(name = "iso2")
    private String iso2;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 8)
    @Column(name = "phonecode")
    private String phonecode;
    

    public Country() {
    }

    public Country(Integer idCountry) {
        this.idCountry = idCountry;
    }
}
