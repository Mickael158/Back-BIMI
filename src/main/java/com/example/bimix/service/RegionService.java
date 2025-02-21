package com.example.bimix.service;

import com.example.bimix.model.Region;
import com.example.bimix.model.Role;
import com.example.bimix.repository.RegionRepository;
import com.example.bimix.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public Region enregistrerRegion(Region region) {
        return this.regionRepository.save(region);
    }
    public Optional<Region> select_Region_By_id(int id) {
        return this.regionRepository.findById(id);
    }

    public void delete_Region_By_id(int id) {
        this.regionRepository.deleteById(id);
    }
    public List<Region> selectAll_Region() {
        return this.regionRepository.findAll();
    }
}
