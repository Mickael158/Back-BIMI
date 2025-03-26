package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "destination")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddestination", nullable = false)
    private Integer idDestination;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "numero_OR")
    private String numero_OR;

    @ManyToOne
    @JoinColumn(name = "idpersonnel")
    private Personnel IdPersonne;

    @Column(name = "objet_mission")
    private String objet_mission;

    @ManyToOne
    @JoinColumn(name = "iditineraire")
    private Itineraire idItineraire;

    @Column(name = "date_arriver_destination")
    private Date date_arriver_destination;

    @Column(name = "code_visa_destination")
    private String code_visa_passage;

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur IdUtilisateur;

    public Integer getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Integer idDestination) {
        this.idDestination = idDestination;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getNumero_OR() {
        return numero_OR;
    }

    public void setNumero_OR(String numero_OR) {
        this.numero_OR = numero_OR;
    }

    public Personnel getIdPersonne() {
        return IdPersonne;
    }

    public void setIdPersonne(Personnel idPersonne) {
        IdPersonne = idPersonne;
    }

    public String getObjet_mission() {
        return objet_mission;
    }

    public void setObjet_mission(String objet_mission) {
        this.objet_mission = objet_mission;
    }

    public Itineraire getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(Itineraire idItineraire) {
        this.idItineraire = idItineraire;
    }

    public Date getDate_arriver_destination() {
        return date_arriver_destination;
    }

    public void setDate_arriver_destination(Date date_arriver_destination) {
        this.date_arriver_destination = date_arriver_destination;
    }

    public String getCode_visa_passage() {
        return code_visa_passage;
    }

    public void setCode_visa_passage(String code_visa_passage) {
        this.code_visa_passage = code_visa_passage;
    }

    public Utilisateur getIdUtilisateur() {
        return IdUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        IdUtilisateur = idUtilisateur;
    }
}
