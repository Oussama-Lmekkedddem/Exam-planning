package com.PE.springprj.services.serviceImpl;

import com.PE.springprj.entities.ElementPedagogique;
import com.PE.springprj.repositories.ElementPedagogiqueRepository;
import com.PE.springprj.services.ElementPedagogiqueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElementPedagogiqueServiceImpl implements ElementPedagogiqueServices {

    @Autowired
    private ElementPedagogiqueRepository elementPedagogiqueRepository;

    @Override
    public void save(ElementPedagogique elementPedagogique) {
        elementPedagogiqueRepository.save(elementPedagogique);
    }

    @Override
    public List<ElementPedagogique> getAllElements() {
        return (List<ElementPedagogique>) elementPedagogiqueRepository.findAll();
    }

    @Override
    public Optional<ElementPedagogique> findById(Long id) {
        return elementPedagogiqueRepository.findById(id);
    }

    @Override
    public void update(ElementPedagogique elementPedagogique) {
        elementPedagogiqueRepository.save(elementPedagogique);
    }

    @Override
    public void delete(Long id) {
        elementPedagogiqueRepository.deleteById(id);
    }

    @Override
    public List<ElementPedagogique> getAllElementWithEnseignant() {
        return elementPedagogiqueRepository.findAllWithEnseignant();
    }
}
