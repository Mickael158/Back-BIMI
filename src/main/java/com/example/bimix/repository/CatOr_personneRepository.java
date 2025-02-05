package com.example.bimix.repository;

import com.example.bimix.model.CatOr_personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatOr_personneRepository extends JpaRepository<CatOr_personne, Integer> {
}
