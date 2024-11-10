package com.PE.springprj.services.serviceImpl;

import com.PE.springprj.entities.*;
import com.PE.springprj.repositories.AdministrateurRepository;
import com.PE.springprj.repositories.PlanificationRepository;
import com.PE.springprj.services.AdministrateurServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdministrateurServicesImpl implements AdministrateurServices {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private PlanificationRepository planificationRepository;

    @Override
    public void save(Administrateur administrateur) {
        administrateurRepository.save(administrateur);
    }

    @Override
    public List<Administrateur> getAllAdministrateurs() {
        return (List<Administrateur>) administrateurRepository.findAll();
    }

    @Override
    public Optional<Administrateur> findById(Long id) {
        return administrateurRepository.findById(id);
    }

    @Override
    public void update(Administrateur administrateur) {
        administrateurRepository.save(administrateur);
    }

    @Override
    public void delete(long id) {
        Optional<Administrateur> optionalAdministrateur = administrateurRepository.findById(id);
        if (optionalAdministrateur.isPresent()) {

            List<Planification> planifications = planificationRepository.findByAdministrateurId(id);
            for (Planification planification : planifications) {
                planification.setAdministrateur(null);
                planificationRepository.save(planification);
            }

            administrateurRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Administrateur vnot found with ID: " + id);
        }

    }
}
