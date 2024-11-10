package com.PE.springprj.services;

import com.PE.springprj.entities.Administrateur;
import com.PE.springprj.entities.Enseignant;
import com.PE.springprj.entities.Examen;
import com.PE.springprj.entities.Salle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExamenServices {

    void save(Examen examen);

    @Transactional
    void saveExamenWithAssociations(Examen examen, List<Salle> salles, List<Enseignant> enseignants, List<Administrateur> administrateurs);

    List<Examen> getAllExamens();

    Optional<Examen> findById(Long id);

    void update(Examen examen);

    void delete(long id);

    boolean isExamenExists(Long elementId, String semester, String year, String type, String session);
}
