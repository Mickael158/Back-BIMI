package com.example.bimix.repository;

import com.example.bimix.model.ServiceM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceM , Integer> {
}
