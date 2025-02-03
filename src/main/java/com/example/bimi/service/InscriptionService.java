package com.example.bimi.service;

import com.example.bimi.model.Fonction;
import com.example.bimi.model.Inscription;
import com.example.bimi.repository.FonctionRepository;
import com.example.bimi.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {
    @Autowired
    private InscriptionRepository inscriptionRepository;

    public Inscription enregistrerInscription(Inscription inscription) {
        return this.inscriptionRepository.save(inscription);
    }
    public Optional<Inscription> select_Inscription_By_id(int id) {
        return this.inscriptionRepository.findById(id);
    }

    public void delete_Inscription_By_id(int id) {
        this.inscriptionRepository.deleteById(id);
    }
    public List<Inscription> selectAll_Inscription() {
        return this.inscriptionRepository.findAll();
    }
}
