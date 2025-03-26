package com.example.bimix.service;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Passage;
import com.example.bimix.repository.DepartRepository;
import com.example.bimix.repository.PassageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassageService {
    @Autowired
    private PassageRepository passageRepository;

    public Passage enregistrerPassage(Passage passage) {
        return this.passageRepository.save(passage);
    }
    public Optional<Passage> select_Passage_By_id(int id) {
        return this.passageRepository.findById(id);
    }
    public Optional<Passage> findPassageByCode_visa_passage(String Code_visa_passage) {
        return this.passageRepository.findPassageByCode_visa_passage(Code_visa_passage);
    }

    public void delete_Passage_By_id(int id) {
        this.passageRepository.deleteById(id);
    }
    public List<Passage> selectAll_Passage() {
        return this.passageRepository.findAll();
    }
}
