package com.example.bimix.service;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Itineraire;
import com.example.bimix.repository.DepartRepository;
import com.example.bimix.repository.ItineraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraireService {
    @Autowired
    private ItineraireRepository itineraireRepository;

    public Itineraire enregistrerItineraire(Itineraire itineraire) {
        return this.itineraireRepository.save(itineraire);
    }
    public Optional<Itineraire> select_Itineraire_By_id(int id) {
        return this.itineraireRepository.findById(id);
    }

    public void delete_Itineraire_By_id(int id) {
        this.itineraireRepository.deleteById(id);
    }
    public List<Itineraire> selectAll_Itineraire() {
        return this.itineraireRepository.findAll();
    }

    public List<Itineraire> findItineraireByIdDepart(int id) {
        return this.itineraireRepository.findItineraireByIdDepart(id);
    }

    public List<Itineraire> enregistrerItineraires (List<Itineraire> itineraireList) {
        return this.itineraireRepository.saveAll(itineraireList);
    }
}
