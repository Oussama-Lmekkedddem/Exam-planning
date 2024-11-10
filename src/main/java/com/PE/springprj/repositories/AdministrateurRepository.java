package com.PE.springprj.repositories;

import com.PE.springprj.entities.Administrateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministrateurRepository extends CrudRepository<Administrateur, Long> {
}
