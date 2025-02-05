package com.example.bimix.service;

import com.example.bimix.model.Personnel;
import com.example.bimix.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void delete_Personnel_By_id(int id) {
        this.personnelRepository.deleteById(id);
    }
    public List<Personnel> selectAll_Inscription() {
        return this.personnelRepository.findAll();
    }
}
