package com.example.bimix.service;

import com.example.bimix.model.CatOr_personne;
import com.example.bimix.repository.CatOr_personneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatOr_personneService {
    @Autowired
    private CatOr_personneRepository catOr_personneRepository;

    public CatOr_personne enregistrerCatOr_personne(CatOr_personne catOr_personne) {
        return this.catOr_personneRepository.save(catOr_personne);
    }
    public Optional<CatOr_personne> select_CatOr_personne_By_id(int id) {
        return this.catOr_personneRepository.findById(id);
    }

    public void delete_CatOr_personne_By_id(int id) {
        this.catOr_personneRepository.deleteById(id);
    }
    public List<CatOr_personne> selectAll_CatOr_personne() {
        return this.catOr_personneRepository.findAll();
    }
}
