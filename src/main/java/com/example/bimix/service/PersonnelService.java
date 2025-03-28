package com.example.bimix.service;

import com.example.bimix.model.Personnel;
import com.example.bimix.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;

    public Personnel enregistrerPersonnel(Personnel personnel) {
        return this.personnelRepository.save(personnel);
    }
    public Optional<Personnel> select_Personnel_By_id(int id) {
        return this.personnelRepository.findById(id);
    }
    public Optional<Personnel> select_Personnel_By_IM(String id) {
        return this.personnelRepository.findPersonIM(id);
    }
    public Optional<Personnel> findPersonByNonAndPrenomAndCinAndCin_du(String matricule,String nom , String Prenom , String CIN , Date Cin_du) {
        return this.personnelRepository.findPersonByImAndNonAndPrenomAndCinAndCin_du(matricule,nom, Prenom , CIN ,Cin_du);
    }
    public void delete_Personnel_By_id(int id) {
        this.personnelRepository.deleteById(id);
    }
    public List<Personnel> selectAll_Inscription() {
        return this.personnelRepository.findAll();
    }
    public List<Personnel> findPersonByNonAndPrenom(String nom , String Prenom) {
        return this.personnelRepository.findPersonByNonAndPrenom(nom , Prenom);
    }
    public List<Personnel> selectAll_Inscription(String cin) {
        return this.personnelRepository.findPersonByCin(cin);
    }
}
