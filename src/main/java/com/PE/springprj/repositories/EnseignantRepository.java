package com.PE.springprj.repositories;

import com.PE.springprj.entities.Enseignant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EnseignantRepository extends CrudRepository<Enseignant, Long>{
    List<Enseignant> findByFiliere(String filiere);
    List<Enseignant> findByDepartement(String departement);
}
