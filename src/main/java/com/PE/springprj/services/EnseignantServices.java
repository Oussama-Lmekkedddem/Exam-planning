package com.PE.springprj.services;

import com.PE.springprj.entities.Enseignant;

import java.util.List;
import java.util.Optional;

public interface EnseignantServices {
    void save(Enseignant enseignant);

    List<Enseignant> getAllEnseignants();

    Optional<Enseignant> findById(Long id);

    void update(Enseignant enseignant);

    void delete(long id);

    List<Enseignant> findByFiliere(String filiere);
    List<Enseignant> findByDepartement(String departement);

    List<Enseignant> getEnseignantsByDayAndTime(String day, String time);
}
