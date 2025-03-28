package com.example.bimix.service;

import com.example.bimix.model.ServiceM;
import com.example.bimix.repository.ServiceRepository;
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
    public Optional<ServiceM> findServiceMByServiceAndDirection(String service , String direction) {
        return this.serviceRepository.findServiceMByServiceAndDirection(service, direction);
    }

    public void delete_ServiceM_By_id(int id) {
        this.serviceRepository.deleteById(id);
    }
    public List<ServiceM> selectAll_ServiceM() {
        return this.serviceRepository.findAll();
    }
}
