package com.example.bimi.repository;

import com.example.bimi.model.Service_Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Service_PersonnelRepository extends JpaRepository<Service_Personnel , Integer> {
}
