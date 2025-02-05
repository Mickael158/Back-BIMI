package com.example.bimix.repository;

import com.example.bimix.model.Service_Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Service_PersonnelRepository extends JpaRepository<Service_Personnel, Integer> {
}
