package com.example.bimi.service;

import com.example.bimi.model.Inscription;
import com.example.bimi.model.Role;
import com.example.bimi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role enregistrerRole(Role role) {
        return this.roleRepository.save(role);
    }
    public Optional<Role> select_Role_By_id(int id) {
        return this.roleRepository.findById(id);
    }

    public void delete_Role_By_id(int id) {
        this.roleRepository.deleteById(id);
    }
    public List<Role> selectAll_Role() {
        return this.roleRepository.findAll();
    }
}
