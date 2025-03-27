package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpaiement", nullable = false)
    private Integer idPaiement;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "date_suivit")
    private Date date_suivit;

    @Column(name = "date_sortie_bon_de_caisse")
    private Date date_sortie_bon_de_caisse;

    @ManyToOne
    @JoinColumn(name = "Iddepart")
    private Depart Iddepart;


    public Integer getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(Integer idPaiement) {
        this.idPaiement = idPaiement;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Date getDate_suivit() {
        return date_suivit;
    }

    public void setDate_suivit(Date date_suivit) {
        this.date_suivit = date_suivit;
    }

    public Date getDate_sortie_bon_de_caisse() {
        return date_sortie_bon_de_caisse;
    }

    public void setDate_sortie_bon_de_caisse(Date date_sortie_bon_de_caisse) {
        this.date_sortie_bon_de_caisse = date_sortie_bon_de_caisse;
    }

    public Depart getIddepart() {
        return Iddepart;
    }

    public void setIddepart(Depart iddepart) {
        Iddepart = iddepart;
    }
}
