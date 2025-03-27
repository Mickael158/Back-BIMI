package com.example.bimix.service;

import com.example.bimix.model.Modif_paiement;
import com.example.bimix.model.Paiement_situation;
import com.example.bimix.repository.Modif_PaiementRepository;
import com.example.bimix.repository.Paiement_SituatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Modif_paiementService {
    @Autowired
    private Modif_PaiementRepository modif_paiementRepository;

    public Modif_paiement enregistrerModif_paiement(Modif_paiement modif_paiement) {
        return this.modif_paiementRepository.save(modif_paiement);
    }
    public Optional<Modif_paiement> select_Modif_paiement_By_id(int id) {
        return this.modif_paiementRepository.findById(id);
    }
    public Optional<Modif_paiement> findModif_paiementByIdpaiement(int idpaiement) {
        return this.modif_paiementRepository.findModif_paiementByIdpaiement(idpaiement);
    }
    public void delete_Modif_paiement_By_id(int id) {
        this.modif_paiementRepository.deleteById(id);
    }
    public List<Modif_paiement> selectAll_Modif_paiement() {
        return this.modif_paiementRepository.findAll();
    }
}
