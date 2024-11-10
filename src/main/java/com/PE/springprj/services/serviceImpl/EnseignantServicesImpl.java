package com.PE.springprj.services.serviceImpl;


import com.PE.springprj.entities.ElementPedagogique;
import com.PE.springprj.entities.Enseignant;
import com.PE.springprj.entities.Examen;
import com.PE.springprj.entities.Planification;
import com.PE.springprj.repositories.*;

import com.PE.springprj.services.TempsLibreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PE.springprj.services.EnseignantServices;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EnseignantServicesImpl implements EnseignantServices {


    @Autowired
    private ElementPedagogiqueRepository elementPedagogiqueRepository;

    @Autowired
    private TempsLibreService tempsLibreService;

    @Autowired
    private TempsLibreRepository tempsLibreRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private final EnseignantRepository enseignantRepository;

    @Autowired
    private PlanificationRepository planificationRepository;

    @Autowired
    public EnseignantServicesImpl(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    @Override
    public void save(Enseignant enseignant) {
        enseignantRepository.save(enseignant);
       tempsLibreService.initTempsLibreForEnseignant(enseignant);
    }

    @Override
    public List<Enseignant> getAllEnseignants() {
        return (List<Enseignant>) enseignantRepository.findAll();
    }

    @Override
    public Optional<Enseignant> findById(Long id) {
        return enseignantRepository.findById(id);
    }

    @Override
    public void update(Enseignant enseignant) {
        enseignantRepository.save(enseignant);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(id);
        if (optionalEnseignant.isPresent()) {
            Enseignant enseignant = optionalEnseignant.get();

            Set<ElementPedagogique> elements = enseignant.getElements();
            for (ElementPedagogique element : elements) {
                element.setEnseignant(null);
                elementPedagogiqueRepository.save(element);
            }

            List<Examen> examens = examenRepository.findByEnseignantId(id);
            for (Examen examen : examens) {
                examen.setEnseignant(null);
                examenRepository.save(examen);
            }

            List<Planification> planifications = planificationRepository.findByEnseignantId(id);
            for (Planification planification : planifications) {
                planification.setEnseignant(null);
                planificationRepository.save(planification);
            }

            enseignantRepository.delete(enseignant);
        } else {
            throw new EntityNotFoundException("Enseignant not found with ID: " + id);
        }
    }

    @Override
    public List<Enseignant> findByFiliere(String filiere) {
        return enseignantRepository.findByFiliere(filiere);
    }

    @Override
    public List<Enseignant> findByDepartement(String departement) {
        return enseignantRepository.findByDepartement(departement);
    }

    @Override
    public List<Enseignant> getEnseignantsByDayAndTime(String day, String time) {
       return tempsLibreRepository.findEnseignantsByDayByTime(day, time);
    }
}
