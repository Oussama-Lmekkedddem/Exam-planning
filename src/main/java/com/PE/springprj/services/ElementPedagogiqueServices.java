package com.PE.springprj.services;

import com.PE.springprj.entities.ElementPedagogique;
import java.util.List;
import java.util.Optional;

public interface ElementPedagogiqueServices {

    void save(ElementPedagogique elementPedagogique);

    List<ElementPedagogique> getAllElements();

    Optional<ElementPedagogique> findById(Long id);

    void update(ElementPedagogique elementPedagogique);

    void delete(Long id);

    List<ElementPedagogique> getAllElementWithEnseignant();
}
