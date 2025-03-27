package com.example.bimix.model;

public class Retour_Api {
    private Depart depart;
    private String code_visa_fin;

    public Retour_Api(Depart depart, String code_visa_fin) {
        this.depart = depart;
        this.code_visa_fin = code_visa_fin;
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
