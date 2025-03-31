package com.example.bimix.repository;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Passage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassageRepository extends JpaRepository<Passage , Integer> {
    @Query(value = """
    select * from passage where code_visa_passage=:code_visa_passage
    """,nativeQuery = true)
    Optional<Passage> findPassageByCode_visa_passage(@Param("code_visa_passage") String code_visa_passage);

    @Query(value = """
    select * from passage where IdUtilisateur=:IdUtilisateur order by idpassage DESC LIMIT :lim
    """,nativeQuery = true)
    List<Passage> findPassageByIdUtilisateurLim(@Param("IdUtilisateur") int IdUtilisateur , @Param("lim") int lim);

    @Query(value = """
    select * from passage order by idpassage DESC LIMIT :lim
    """,nativeQuery = true)
    List<Passage> findPassageLimiter(@Param("lim") int lim);
}
