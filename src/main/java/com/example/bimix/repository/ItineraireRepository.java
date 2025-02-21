package com.example.bimix.repository;

import com.example.bimix.model.Itineraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraireRepository extends JpaRepository<Itineraire , Integer> {
}
