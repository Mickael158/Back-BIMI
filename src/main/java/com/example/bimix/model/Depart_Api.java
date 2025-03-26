package com.example.bimix.model;

import java.util.List;

public class Depart_Api {
    private Depart depart;
    private List<Itineraire> itineraires;

    public Depart_Api(Depart depart, List<Itineraire> itineraires) {
        this.depart = depart;
        this.itineraires = itineraires;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public List<Itineraire> getItineraires() {
        return itineraires;
    }

    public void setItineraires(List<Itineraire> itineraires) {
        this.itineraires = itineraires;
    }
}
