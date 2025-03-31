package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

public class Depart_select {
    private Integer idDepart;

    private Date dates;

    private String numero_OR;

    private Personnel IdPersonne;

    private String Objet_mission;

    private Date date_depart;

    private Date date_arriver;

    private String code_Visa_depart;

    private String code_avance;

    private Date engagement;

    private String bordereau;

    private String soa;

    private Utilisateur IdUtilisateur;
    private List<Itineraire> itineraires;

    public Integer getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(Integer idDepart) {
        this.idDepart = idDepart;
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
        return Objet_mission;
    }

    public void setObjet_mission(String objet_mission) {
        Objet_mission = objet_mission;
    }

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public Date getDate_arriver() {
        return date_arriver;
    }

    public void setDate_arriver(Date date_arriver) {
        this.date_arriver = date_arriver;
    }

    public String getCode_Visa_depart() {
        return code_Visa_depart;
    }

    public void setCode_Visa_depart(String code_Visa_depart) {
        this.code_Visa_depart = code_Visa_depart;
    }

    public String getCode_avance() {
        return code_avance;
    }

    public void setCode_avance(String code_avance) {
        this.code_avance = code_avance;
    }

    public Date getEngagement() {
        return engagement;
    }

    public void setEngagement(Date engagement) {
        this.engagement = engagement;
    }

    public String getBordereau() {
        return bordereau;
    }

    public void setBordereau(String bordereau) {
        this.bordereau = bordereau;
    }

    public String getSoa() {
        return soa;
    }

    public void setSoa(String soa) {
        this.soa = soa;
    }

    public Utilisateur getIdUtilisateur() {
        return IdUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        IdUtilisateur = idUtilisateur;
    }

    public List<Itineraire> getItineraires() {
        return itineraires;
    }

    public void setItineraires(List<Itineraire> itineraires) {
        this.itineraires = itineraires;
    }
}
