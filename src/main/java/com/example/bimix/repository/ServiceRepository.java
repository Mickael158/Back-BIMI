package com.example.bimix.repository;

import com.example.bimix.model.Fonction;
import com.example.bimix.model.ServiceM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceM , Integer> {

    @Query(value = """
    select service.* from service
        join direction d on d.idDirection = service.idDirection
            where service.nom=:service_nom and d.nom=:d_nom
    """,nativeQuery = true)
    Optional<ServiceM> findServiceMByServiceAndDirection(@Param("service_nom") String service_nom , @Param("d_nom") String d_nom);
}
