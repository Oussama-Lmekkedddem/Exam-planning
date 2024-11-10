package com.PE.springprj.repositories;

import com.PE.springprj.entities.Planification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {

    void deleteByEnseignantId(Long enseignantId);
    void deleteByAdministrateurId(Long administrateurId);

    List<Planification> findByEnseignantId(Long enseignantId);
    List<Planification> findByAdministrateurId(Long administrateurId);

    @Modifying
    @Query(value = "INSERT INTO planification (examen_id, salle_id, enseignant_id, administrateur_id) VALUES (:examenId, :salleId, :enseignantId, :administrateurId)", nativeQuery = true)
    void savePlanification(Long examenId, Long salleId, Long enseignantId, Long administrateurId);
}
