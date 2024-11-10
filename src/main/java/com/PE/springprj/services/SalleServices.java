package com.PE.springprj.services;


import com.PE.springprj.entities.Salle;

import java.util.List;
import java.util.Optional;

public interface SalleServices {

    List<Salle> getAllSalles();

    Optional<Salle> findById(Long id);
}
