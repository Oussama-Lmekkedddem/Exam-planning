package com.PE.springprj.repositories;

import com.PE.springprj.entities.ElementPedagogique;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ElementPedagogiqueRepository extends CrudRepository<ElementPedagogique, Long> {
    @Query("SELECT e FROM ElementPedagogique e WHERE e.enseignant IS NOT NULL")
    List<ElementPedagogique> findAllWithEnseignant();
}
