package com.example.bimix.service;

import com.example.bimix.model.Destination;
import com.example.bimix.model.Direction;
import com.example.bimix.model.Passage;
import com.example.bimix.model.Retour;
import com.example.bimix.repository.DestinationRepository;
import com.example.bimix.repository.DirectionRepository;
import com.example.bimix.repository.RetourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetourService {
    @Autowired
    private RetourRepository retourRepository;

    public Retour enregistrerRetour(Retour retour) {
        return this.retourRepository.save(retour);
    }
    public Optional<Retour> select_Retour_By_id(int id) {
        return this.retourRepository.findById(id);
    }
    public Optional<Retour> findRetourBynumero_OR(String or) {
        return this.retourRepository.findRetourBynumero_OR(or);
    }
    public Optional<Retour> findRetourByCode_visa_fin(String code_visa_fin) {
        return this.retourRepository.findRetourByCode_visa_fin(code_visa_fin);
    }

    public void delete_Retour_By_id(int id) {
        this.retourRepository.deleteById(id);
    }
    public List<Retour> findRetourByIdUtilisateurLim(int idUtilisateur , int lim) {
        return this.retourRepository.findRetourByIdUtilisateurLim(idUtilisateur , lim);
    }
    public List<Retour> findRetourLimiter(int lim) {
        return this.retourRepository.findRetourLimiter(lim);
    }
}
