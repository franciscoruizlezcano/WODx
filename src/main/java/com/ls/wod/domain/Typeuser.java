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
@Table(name = "typeuser")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Typeuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeUser")
    private Integer idTypeUser;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 13)
    @Column(name = "description")
    private String description;
    
    @Size(min = 1, max = 12)
    @Column(name = "role")
    private String role;

    public Typeuser() {
    }

    public Typeuser(Integer idTypeUser) {
        this.idTypeUser = idTypeUser;
    }
}
