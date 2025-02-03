package com.example.bimi.service;

import com.example.bimi.model.Role;
import com.example.bimi.model.ServiceM;
import com.example.bimi.repository.RoleRepository;
import com.example.bimi.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public ServiceM enregistrerServiceM(ServiceM serviceM) {
        return this.serviceRepository.save(serviceM);
    }
    public Optional<ServiceM> select_ServiceM_By_id(int id) {
        return this.serviceRepository.findById(id);
    }

    public void delete_ServiceM_By_id(int id) {
        this.serviceRepository.deleteById(id);
    }
    public List<ServiceM> selectAll_ServiceM() {
        return this.serviceRepository.findAll();
    }
}
