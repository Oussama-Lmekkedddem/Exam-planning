package com.PE.springprj.repositories;

import com.PE.springprj.entities.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    boolean existsByElementPedagogiqueIdAndSemestreAndAnneeUniversitaireAndTypeAndSession(
            Long elementId, String semester, String year, String type, String session);
    List<Examen> findByEnseignantId(Long enseignantId);
}
