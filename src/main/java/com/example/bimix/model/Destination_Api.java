package com.example.bimix.model;

import java.util.List;

public class Destination_Api {
    private Depart depart;
    private Itineraire itineraires;
    private String Code_visa_destination;

    public Destination_Api(Depart depart, Itineraire itineraires, String code_visa_destination) {
        this.depart = depart;
        this.itineraires = itineraires;
        Code_visa_destination = code_visa_destination;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public Itineraire getItineraires() {
        return itineraires;
    }

    public void setItineraires(Itineraire itineraires) {
        this.itineraires = itineraires;
    }

    public String getCode_visa_destination() {
        return Code_visa_destination;
    }

    public void setCode_visa_destination(String code_visa_destination) {
        Code_visa_destination = code_visa_destination;
    }
}
