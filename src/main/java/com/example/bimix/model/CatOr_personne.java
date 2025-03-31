package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "cator_personne")
public class CatOr_personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcator_personne", nullable = false)
    private Integer IdCatOr_personne;

    @Column(name = "dates")
    private Date dates;

    @ManyToOne
    @JoinColumn(name = "idcator")
    private CatOR IdCatOr;

    @ManyToOne
    @JoinColumn(name = "idpersonnel")
    private Personnel idPersonnel;

    public Integer getIdCatOr_personne() {
        return IdCatOr_personne;
    }

    public void setIdCatOr_personne(Integer idCatOr_personne) {
        IdCatOr_personne = idCatOr_personne;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public CatOR getIdCatOr() {
        return IdCatOr;
    }

    public void setIdCatOr(CatOR idCatOr) {
        IdCatOr = idCatOr;
    }

    public Personnel getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Personnel idPersonnel) {
        this.idPersonnel = idPersonnel;
    }
}
