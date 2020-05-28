package com.ls.wod.domain;

import org.springframework.format.annotation.DateTimeFormat;
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


/**
 *
 * @author francisco
 */

@Entity
@Table(name = "user")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "password"})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;

    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;

    @Size(min = 1, max = 60)
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @Column(name = "creationdate")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date creationdate;

    @Column(name = "updateddate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date updateddate;

    @Column(name = "deletiondate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date deletiondate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;

    @JoinColumn(name = "idCompany", referencedColumnName = "idCompany")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @JoinColumn(name = "idPerson", referencedColumnName = "idPerson")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Person person;

    @JoinColumn(name = "idTypeUser", referencedColumnName = "idTypeUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Typeuser typeuser;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }
}
