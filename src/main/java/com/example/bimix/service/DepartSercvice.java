package com.example.bimix.service;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Transport;
import com.example.bimix.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class DepartSercvice {
    @Autowired
    private DepartRepository departRepository;

    public Depart enregistrerDepart(Depart depart) {
        return this.departRepository.save(depart);
    }
    public Optional<Depart> select_Depart_By_id(int id) {
        return this.departRepository.findById(id);
    }

    public void delete_Depart_By_id(int id) {
        this.departRepository.deleteById(id);
    }
    public List<Depart> selectAll_Depart() {
        return this.departRepository.findAll();
    }

    public  Date ajouterJour(Timestamp timestamp , int jour) {
        Instant instant = timestamp.toInstant();

        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate datePlusUnJour = localDate.plusDays(jour);

        return Date.valueOf(datePlusUnJour);
    }
}
