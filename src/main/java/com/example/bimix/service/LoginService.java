package com.example.bimix.service;

import com.example.bimix.model.Personnel;
import com.example.bimix.model.Utilisateur;
import com.example.bimix.repository.PersonnelRepository;
import com.example.bimix.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private PersonnelRepository personneRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur login(String matricule) throws Exception {
        Optional<Personnel> personne = this.personneRepository.findEmailPerson(matricule);
        if (personne.isPresent()){
            Optional<Utilisateur> utilisateur = this.utilisateurRepository.findUtilisateur(personne.get().getIdPersonnel());
            if (utilisateur.isPresent()){
                return utilisateur.get();
            }
            else {
                throw new Exception("Mot de passe incorrect");
            }
        }
        else {
            throw new Exception("Email incorrect");
        }
    }
}
