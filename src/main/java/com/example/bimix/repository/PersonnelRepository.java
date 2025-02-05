package com.example.bimix.repository;

import com.example.bimix.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    @Query(value = """
    SELECT * FROM personnel WHERE matricule=:matricule
    """,nativeQuery = true)
    Optional<Personnel> findEmailPerson(@Param("matricule") String matricule);
}
