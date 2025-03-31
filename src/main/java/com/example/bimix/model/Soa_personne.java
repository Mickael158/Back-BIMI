package com.example.bimix.model;

public class Soa_personne {

    private Fonction_personnel fonction_personnel;
    private Service_Personnel service_personnel;

    public Soa_personne(Fonction_personnel fonction_personnel, Service_Personnel service_personnel) {
        this.fonction_personnel = fonction_personnel;
        this.service_personnel = service_personnel;
    }

    public Fonction_personnel getFonction_personnel() {
        return fonction_personnel;
    }

    public void setFonction_personnel(Fonction_personnel fonction_personnel) {
        this.fonction_personnel = fonction_personnel;
    }

    public Service_Personnel getService_personnel() {
        return service_personnel;
    }

    public void setService_personnel(Service_Personnel service_personnel) {
        this.service_personnel = service_personnel;
    }
}
