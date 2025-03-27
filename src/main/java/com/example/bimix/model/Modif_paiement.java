package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "modif_paiment")
public class Modif_paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmodif_paiment", nullable = false)
    private Integer idmodif_paiment;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "modif")
    private String modif;

    @ManyToOne
    @JoinColumn(name = "Idpaiement")
    private Paiement Idpaiement;

    @ManyToOne
    @JoinColumn(name = "Idutilisateur")
    private Utilisateur IdUtilisateur;

    public Integer getIdmodif_paiment() {
        return idmodif_paiment;
    }

    public void setIdmodif_paiment(Integer idmodif_paiment) {
        this.idmodif_paiment = idmodif_paiment;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Paiement getIdpaiement() {
        return Idpaiement;
    }

    public String getModif() {
        return modif;
    }

    public void setModif(String modif) {
        this.modif = modif;
    }

    public void setIdpaiement(Paiement idpaiement) {
        Idpaiement = idpaiement;
    }

    public Utilisateur getIdUtilisateur() {
        return IdUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        IdUtilisateur = idUtilisateur;
    }
}
