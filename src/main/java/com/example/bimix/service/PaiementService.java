package com.example.bimix.service;

import com.example.bimix.model.*;
import com.example.bimix.repository.DepartRepository;
import com.example.bimix.repository.ItineraireRepository;
import com.example.bimix.repository.PaiementRepository;
import com.example.bimix.repository.Paiement_SituatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private DepartRepository departRepository;

    @Autowired
    private Paiement_SituatioRepository paiement_situatioRepository;

    @Autowired
    private ItineraireRepository itineraireRepository;
    public Paiement enregistrerPaiement(Paiement paiement) {
        return this.paiementRepository.save(paiement);
    }
    public Optional<Paiement> select_Paiement_By_id(int id) {
        return this.paiementRepository.findById(id);
    }
    public Optional<Paiement> findPaiementBydeaprt(int iddepart) {
        return this.paiementRepository.findPaiementByDepart(iddepart);
    }
    public void delete_Paiement_By_id(int id) {
        this.paiementRepository.deleteById(id);
    }
    public void updatePaiement(Date dateSuivit, Date dateSortieBonDeCaisse, Integer idPaiement) {
        this.paiementRepository.updatePaiement(dateSuivit,dateSortieBonDeCaisse,idPaiement);
    }
    public List<Paiement> selectAll_Paiement() {
        return this.paiementRepository.findAll();
    }
    public Optional<Paiement_api> selectPaimentByOr(String Or) {
        Optional<Depart> depart = this.departRepository.findDepartBynumero_OR(Or);
        List<Itineraire> itineraires ;
        if (depart.isPresent()){
            itineraires = this.itineraireRepository.findItineraireByIdDepart(depart.get().getIdDepart());
        }
        else {
            depart.isEmpty();
            itineraires = new ArrayList<>();
        }
        Optional<Paiement> paiement = this.paiementRepository.findPaiementByDepart(depart.get().getIdDepart());
        List<Paiement_situation> paiement_situations;
        if (paiement.isPresent()){
            paiement_situations  = this.paiement_situatioRepository.findPaiement_situationByIdpaiement(paiement.get().getIdPaiement());
        }
        else {
            paiement_situations = new ArrayList<>();
            paiement.isEmpty();
        }
        return Optional.of(new Paiement_api(depart ,itineraires , paiement, paiement_situations));
    }
}
