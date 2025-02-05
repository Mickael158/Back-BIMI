package com.example.bimix.repository;

import com.example.bimix.model.ServiceM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceM, Integer> {
}
