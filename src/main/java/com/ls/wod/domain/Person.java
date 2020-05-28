package com.ls.wod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author francisco
 */

@Entity
@Table(name = "person")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson")
    private Integer idPerson;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(name = "lastname")
    private String lastname;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    
    @JoinColumn(name = "idPhone", referencedColumnName = "idPhone")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Phone phone;
    

    public Person() {
    }

    public Person(Integer idPerson) {
        this.idPerson = idPerson;
    }
}
