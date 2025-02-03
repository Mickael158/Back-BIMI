package com.example.bimi.repository;

import com.example.bimi.model.ServiceM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceM, Integer> {
}
