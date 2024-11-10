package com.PE.springprj.services.serviceImpl;

import com.PE.springprj.entities.Salle;
import com.PE.springprj.repositories.SalleRepository;
import com.PE.springprj.services.SalleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleServicesImpl implements SalleServices {


    @Autowired
    private SalleRepository salleRepository;

    @Override
    public List<Salle> getAllSalles() {
        return (List<Salle>) salleRepository.findAll();
    }

    @Override
    public Optional<Salle> findById(Long id) {
        return salleRepository.findById(id);
    }
}
