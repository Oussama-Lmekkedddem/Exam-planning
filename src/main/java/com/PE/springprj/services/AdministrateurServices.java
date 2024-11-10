package com.PE.springprj.services;

import com.PE.springprj.entities.Administrateur;

import java.util.List;
import java.util.Optional;

public interface AdministrateurServices {

    void save(Administrateur administrateur);

    List<Administrateur> getAllAdministrateurs();

    Optional<Administrateur> findById(Long id);

    void update(Administrateur administrateur);

    void delete(long id);
}
