package com.example.bimix.model;

public class Retour_Api {
    private Depart depart;
    private Soa_personne soa_personne;
    private String code_visa_fin;

    public Retour_Api(Depart depart, Soa_personne soa_personne, String code_visa_fin) {
        this.depart = depart;
        this.soa_personne = soa_personne;
        this.code_visa_fin = code_visa_fin;
    }

    public Soa_personne getSoa_personne() {
        return soa_personne;
    }

    public void setSoa_personne(Soa_personne soa_personne) {
        this.soa_personne = soa_personne;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public String getCode_visa_fin() {
        return code_visa_fin;
    }

    public void setCode_visa_fin(String code_visa_fin) {
        this.code_visa_fin = code_visa_fin;
    }
}
