package com.example.bimix.repository;

import com.example.bimix.model.Fonction_personnel;
import com.example.bimix.model.Service_Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Service_PersonnelRepository extends JpaRepository<Service_Personnel, Integer> {
    @Query(value = """
    SELECT *
    FROM service_personnel
    WHERE idPersonnel =:idPersonnel
      AND dates = (SELECT MAX(dates) FROM service_personnel WHERE idPersonnel =:idPersonnel);
    """,nativeQuery = true)
    Optional<Service_Personnel> findService_PersonnelMaxByIdPersonnel(@Param("idPersonnel") int idPersonnel);

    @Query(value = """
    select * from service_personnel where idPersonnel=:idPersonnel AND idService=:idService
    """,nativeQuery = true)
    Optional<Fonction_personnel> findFonction_personnelByIdPersonnelAndIdService(@Param("idPersonnel") int idPersonnel, @Param("idService") int idService);
}
