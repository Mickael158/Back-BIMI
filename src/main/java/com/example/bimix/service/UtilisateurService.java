package com.example.bimix.service;

import com.example.bimix.model.Utilisateur;
import com.example.bimix.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur enregistrerUtilisateur(Utilisateur utilisateur) {
        return this.utilisateurRepository.save(utilisateur);
    }
    public Optional<Utilisateur> select_Utilisateur_By_id(int id) {
        return this.utilisateurRepository.findById(id);
    }
    public Optional<Utilisateur> findUtilisateur(int id) {
        return this.utilisateurRepository.findUtilisateur(id);
    }

    public void delete_Utilisateur_By_id(int id) {
        this.utilisateurRepository.deleteById(id);
    }
    public List<Utilisateur> selectAll_Utilisateur() {
        return this.utilisateurRepository.findAll();
    }
}
