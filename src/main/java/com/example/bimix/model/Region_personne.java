package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "region_personne")
public class Region_personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idregion_personne", nullable = false)
    private Integer idregion_personne;

    @Column(name = "dates")
    private Date dates;

    @ManyToOne
    @JoinColumn(name = "idRegion")
    private Fonction idRegion;

    @ManyToOne
    @JoinColumn(name = "idPersonnel")
    private Personnel idPersonnel;

    public Integer getIdregion_personne() {
        return idregion_personne;
    }

    public void setIdregion_personne(Integer idregion_personne) {
        this.idregion_personne = idregion_personne;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Fonction getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Fonction idRegion) {
        this.idRegion = idRegion;
    }

    public Personnel getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Personnel idPersonnel) {
        this.idPersonnel = idPersonnel;
    }
}
