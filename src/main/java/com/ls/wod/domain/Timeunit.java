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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author francisco
 */
@Entity
@Table(name = "timeunit")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Timeunit implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTimeUnit")
    private Integer idTimeUnit;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;

    public Timeunit() {
    }

    public Timeunit(Integer idTimeUnit) {
        this.idTimeUnit = idTimeUnit;
    }

    public Timeunit(Integer idTimeUnit, String description) {
        this.idTimeUnit = idTimeUnit;
        this.description = description;
    }
    
}
