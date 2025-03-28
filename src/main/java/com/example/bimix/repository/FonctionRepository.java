package com.example.bimix.repository;

import com.example.bimix.model.CatOR;
import com.example.bimix.model.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FonctionRepository extends JpaRepository<Fonction, Integer> {
    @Query(value = """
    SELECT * from fonction where nom=:nom
    """,nativeQuery = true)
    Optional<Fonction> findFonctionByNom(@Param("nom") String nom);
}
