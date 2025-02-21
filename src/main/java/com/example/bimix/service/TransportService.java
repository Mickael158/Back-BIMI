package com.example.bimix.service;

import com.example.bimix.model.CatOR;
import com.example.bimix.model.Transport;
import com.example.bimix.repository.CatORRepository;
import com.example.bimix.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportService {
    @Autowired
    private TransportRepository transportRepository;

    public Transport enregistrerTransport(Transport transport) {
        return this.transportRepository.save(transport);
    }
    public Optional<Transport> select_Transport_By_id(int id) {
        return this.transportRepository.findById(id);
    }

    public void delete_Transport_By_id(int id) {
        this.transportRepository.deleteById(id);
    }
    public List<Transport> selectAll_Transport() {
        return this.transportRepository.findAll();
    }
}
