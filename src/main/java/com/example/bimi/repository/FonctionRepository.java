package com.example.bimi.repository;

import com.example.bimi.model.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FonctionRepository extends JpaRepository<Fonction, Integer> {
}
