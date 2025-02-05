package com.example.bimix.service;

import com.example.bimix.model.Service_Personnel;
import com.example.bimix.repository.Service_PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Service_PersonnelService {
    @Autowired
    private Service_PersonnelRepository servicePersonnelRepository;

    public Service_Personnel enregistrerService_Personnel(Service_Personnel service_personnel) {
        return this.servicePersonnelRepository.save(service_personnel);
    }
    public Optional<Service_Personnel> select_Service_Personnel_By_id(int id) {
        return this.servicePersonnelRepository.findById(id);
    }

    public void delete_Service_Personnel_By_id(int id) {
        this.servicePersonnelRepository.deleteById(id);
    }
    public List<Service_Personnel> selectAll_Service_Personnel() {
        return this.servicePersonnelRepository.findAll();
    }
}
