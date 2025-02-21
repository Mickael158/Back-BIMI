package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "depart")
public class Depart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepart", nullable = false)
    private Integer idDepart;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "IM_mission")
    private String IM_mission;


    @Column(name = "numero_OR")
    private String numero_OR;

    @Column(name = "date_depart")
    private Timestamp date_depart;

    @Column(name = "date_arriver")
    private Date date_arriver;

    @Column(name = "code_Visa")
    private String code_Visa;

    @Column(name = "avance")
    private String avance;

    @ManyToOne
    @JoinColumn(name = "IdPersonne")
    private Personnel IdPersonne;

    @ManyToOne
    @JoinColumn(name = "IdUtilisateur")
    private Utilisateur IdUtilisateur;

    public Utilisateur getIdUtilisateur() {
        return IdUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        IdUtilisateur = idUtilisateur;
    }

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

    public String getIM_mission() {
        return IM_mission;
    }

    public void setIM_mission(String IM_mission) {
        this.IM_mission = IM_mission;
    }

    public String getNumero_OR() {
        return numero_OR;
    }

    public void setNumero_OR(String numero_OR) {
        this.numero_OR = numero_OR;
    }

    public Timestamp getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Timestamp date_depart) {
        this.date_depart = date_depart;
    }

    public Date getDate_arriver() {
        return date_arriver;
    }

    public void setDate_arriver(Date date_arriver) {
        this.date_arriver = date_arriver;
    }

    public String getCode_Visa() {
        return code_Visa;
    }

    public void setCode_Visa(String code_Visa) {
        this.code_Visa = code_Visa;
    }

    public String getAvance() {
        return avance;
    }

    public void setAvance(String avance) {
        this.avance = avance;
    }

    public Personnel getIdPersonne() {
        return IdPersonne;
    }

    public void setIdPersonne(Personnel idPersonne) {
        IdPersonne = idPersonne;
    }
}
