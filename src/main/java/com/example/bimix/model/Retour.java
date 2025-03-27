package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "retour")
public class Retour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idretour", nullable = false)
    private Integer idRetour;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "numero_OR")
    private String numero_OR;

    @ManyToOne
    @JoinColumn(name = "idpersonnel")
    private Personnel IdPersonne;

    @Column(name = "objet_mission")
    private String objet_mission;


    @Column(name = "date_visa_fin")
    private Date date_visa_fin;

    @Column(name = "code_visa_fin")
    private String code_visa_passage;

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur IdUtilisateur;

    public Integer getIdRetour() {
        return idRetour;
    }

    public void setIdRetour(Integer idRetour) {
        this.idRetour = idRetour;
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

    public Date getDate_visa_fin() {
        return date_visa_fin;
    }

    public void setDate_visa_fin(Date date_visa_fin) {
        this.date_visa_fin = date_visa_fin;
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
