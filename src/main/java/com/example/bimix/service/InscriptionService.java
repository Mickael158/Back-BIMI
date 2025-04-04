package com.example.bimix.service;

import com.example.bimix.model.Inscription;
import com.example.bimix.repository.InscriptionRepository;
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
    public Optional<Inscription> selectInscriptionByIm(String im) {
        return this.inscriptionRepository.selectInscriptionByIm(im);
    }

    public void delete_Inscription_By_id(int id) {
        this.inscriptionRepository.deleteById(id);
    }
    public void deleteByMatricule(String IM) {
        this.inscriptionRepository.deleteByMatricule(IM);
    }
    public List<Inscription> selectAll_Inscription() {
        return this.inscriptionRepository.findAll();
    }
}
