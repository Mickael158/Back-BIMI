package com.example.bimix.repository;

import com.example.bimix.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    @Query(value = """
    SELECT u.*
        from utilisateur u
            join personnel p on u.idPersonnel = p.idPersonnel
        where u.idPersonnel=:idPersonne
    """,nativeQuery = true)
    Optional<Utilisateur> findUtilisateur(@Param("idPersonne") int idPersonne);
}
