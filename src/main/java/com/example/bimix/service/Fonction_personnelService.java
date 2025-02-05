package com.example.bimix.service;

import com.example.bimix.model.Fonction_personnel;
import com.example.bimix.repository.Fonction_personnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Fonction_personnelService {
    @Autowired
    private Fonction_personnelRepository fonction_personnelRepository;

    public Fonction_personnel enregistrerFonction_personnel(Fonction_personnel fonction_personnel) {
        return this.fonction_personnelRepository.save(fonction_personnel);
    }
    public Optional<Fonction_personnel> select_Fonction_personnel_By_id(int id) {
        return this.fonction_personnelRepository.findById(id);
    }

    public void delete_Fonction_personnel_By_id(int id) {
        this.fonction_personnelRepository.deleteById(id);
    }
    public List<Fonction_personnel> selectAll_Fonction() {
        return this.fonction_personnelRepository.findAll();
    }
}
