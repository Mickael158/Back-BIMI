package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "depart")
public class Depart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddepart", nullable = false)
    private Integer idDepart;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "numero_OR")
    private String numero_OR;

    @ManyToOne
    @JoinColumn(name = "idpersonnel")
    private Personnel IdPersonne;

    @Column(name = "Objet_mission")
    private String Objet_mission;

    @Column(name = "date_depart")
    private Date date_depart;

    @Column(name = "date_arriver")
    private Date date_arriver;

    @Column(name = "code_Visa_depart")
    private String code_Visa_depart;

    @Column(name = "code_avance")
    private String code_avance;

    @Column(name = "engagement")
    private String engagement;

    @Column(name = "bordereau")
    private String bordereau;

    @Column(name = "soa")
    private String soa;

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur IdUtilisateur;

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

    public String getEngagement() {
        return engagement;
    }

    public void setEngagement(String engagement) {
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
}
