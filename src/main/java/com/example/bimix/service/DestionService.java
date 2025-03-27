package com.example.bimix.service;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Destination;
import com.example.bimix.repository.DepartRepository;
import com.example.bimix.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestionService {
    @Autowired
    private DestinationRepository destinationRepository;

    public Destination enregistrerDestination(Destination destination) {
        return this.destinationRepository.save(destination);
    }
    public Optional<Destination> select_Destination_By_id(int id) {
        return this.destinationRepository.findById(id);
    }
    public Optional<Destination> findDestinationBynumero_OR(String or) {
        return this.destinationRepository.findDestinationBynumero_OR(or);
    }
    public Optional<Destination> findDestinationByCode_visa_destination(String code_visa_passage) {
        return this.destinationRepository.findDestinationByCode_visa_destination(code_visa_passage);
    }

    public void delete_Destination_By_id(int id) {
        this.destinationRepository.deleteById(id);
    }
    public List<Destination> selectAll_Destination() {
        return this.destinationRepository.findAll();
    }
}
