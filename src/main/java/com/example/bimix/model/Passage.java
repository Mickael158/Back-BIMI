package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "passage")
public class Passage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpassage", nullable = false)
    private Integer idpassage;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "numero_OR")
    private String numero_OR;

    @ManyToOne
    @JoinColumn(name = "idpersonnel")
    private Personnel IdPersonne;

    @Column(name = "objet_mission")
    private String objet_mission;

    @Column(name = "date_passage")
    private Date date_passage;

    @Column(name = "code_visa_passage")
    private String code_visa_passage;

    @ManyToOne
    @JoinColumn(name = "iditineraire")
    private Itineraire idItineraire;

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur IdUtilisateur;

    public Integer getIdpassage() {
        return idpassage;
    }

    public void setIdpassage(Integer idpassage) {
        this.idpassage = idpassage;
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

    public Date getDate_passage() {
        return date_passage;
    }

    public void setDate_passage(Date date_passage) {
        this.date_passage = date_passage;
    }

    public String getCode_visa_passage() {
        return code_visa_passage;
    }

    public void setCode_visa_passage(String code_visa_passage) {
        this.code_visa_passage = code_visa_passage;
    }

    public Itineraire getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(Itineraire idItineraire) {
        this.idItineraire = idItineraire;
    }

    public Utilisateur getIdUtilisateur() {
        return IdUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        IdUtilisateur = idUtilisateur;
    }
}
