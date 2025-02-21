package com.example.bimix.repository;

import com.example.bimix.model.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartRepository extends JpaRepository<Depart , Integer> {
}
