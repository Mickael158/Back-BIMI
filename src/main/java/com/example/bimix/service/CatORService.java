package com.example.bimix.service;

import com.example.bimix.model.CatOR;
import com.example.bimix.repository.CatORRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatORService {
    @Autowired
    private CatORRepository catORRepository;

    public CatOR enregistrerCatOR(CatOR catOR) {
        return this.catORRepository.save(catOR);
    }
    public Optional<CatOR> select_CatOR_By_id(int id) {
        return this.catORRepository.findById(id);
    }
    public Optional<CatOR> findCatOrByNomAndCodeGradeAndIndice(String nom, String Code_grade, String Indice) {
        return this.catORRepository.findCatOrByNomAndCodeGradeAndIndice(nom, Code_grade , Indice);
    }

    public void delete_CatOR_By_id(int id) {
        this.catORRepository.deleteById(id);
    }
    public List<CatOR> selectAll_CatOR() {
        return this.catORRepository.findAll();
    }
}
