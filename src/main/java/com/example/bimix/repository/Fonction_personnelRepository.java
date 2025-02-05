package com.example.bimix.repository;

import com.example.bimix.model.Fonction_personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fonction_personnelRepository extends JpaRepository<Fonction_personnel, Integer> {
}
