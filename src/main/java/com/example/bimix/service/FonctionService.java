package com.example.bimix.service;

import com.example.bimix.model.Fonction;
import com.example.bimix.repository.FonctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FonctionService {
    @Autowired
    private FonctionRepository fonctionRepository;

    public Fonction enregistrerFonction(Fonction fonction) {
        return this.fonctionRepository.save(fonction);
    }
    public Optional<Fonction> select_Fonction_By_id(int id) {
        return this.fonctionRepository.findById(id);
    }
    public Optional<Fonction> findFonctionByNom(String nom) {
        return this.fonctionRepository.findFonctionByNom(nom);
    }

    public void delete_Fonction_By_id(int id) {
        this.fonctionRepository.deleteById(id);
    }
    public List<Fonction> selectAll_Fonction() {
        return this.fonctionRepository.findAll();
    }
}
