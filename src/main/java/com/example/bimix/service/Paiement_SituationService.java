package com.example.bimix.service;

import com.example.bimix.model.Paiement;
import com.example.bimix.model.Paiement_situation;
import com.example.bimix.repository.PaiementRepository;
import com.example.bimix.repository.Paiement_SituatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Paiement_SituationService {
    @Autowired
    private Paiement_SituatioRepository paiement_situatioRepository;

    public Paiement_situation enregistrerPaiement_situation(Paiement_situation paiement_situation) {
        return this.paiement_situatioRepository.save(paiement_situation);
    }
    public Optional<Paiement_situation> select_Paiement_situation_By_id(int id) {
        return this.paiement_situatioRepository.findById(id);
    }
    public List<Paiement_situation> findPaiement_situationByIdpaiement(int idpaiement) {
        return this.paiement_situatioRepository.findPaiement_situationByIdpaiement(idpaiement);
    }
    public void delete_Paiement_situation_By_id(int id) {
        this.paiement_situatioRepository.deleteById(id);
    }
    public List<Paiement_situation> selectAll_Paiement() {
        return this.paiement_situatioRepository.findAll();
    }
}
