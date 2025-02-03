package com.example.bimi.service;

import com.example.bimi.model.Role;
import com.example.bimi.model.Utilisateur;
import com.example.bimi.repository.RoleRepository;
import com.example.bimi.repository.UtilisateurRepository;
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

    public void delete_Utilisateur_By_id(int id) {
        this.utilisateurRepository.deleteById(id);
    }
    public List<Utilisateur> selectAll_Utilisateur() {
        return this.utilisateurRepository.findAll();
    }
}
