package com.PE.springprj.services.serviceImpl;

import com.PE.springprj.entities.*;
import com.PE.springprj.repositories.ExamenRepository;
import com.PE.springprj.repositories.PlanificationRepository;
import com.PE.springprj.services.ExamenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ExamenServicesImpl implements ExamenServices {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private PlanificationRepository planificationRepository;

    @Override
    public void save(Examen examen) {
        examenRepository.save(examen);
    }

    @Override
    public void saveExamenWithAssociations(Examen examen, List<Salle> salles, List<Enseignant> enseignants, List<Administrateur> administrateurs) {

        examenRepository.save(examen);

        int tailleListes = salles.size();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < tailleListes; i++) {
            final int index = i;

            executorService.submit(() -> {
                Planification planification = new Planification();
                planification.setExamen(examen);
                planification.setEnseignant(enseignants.get(index));
                planification.setAdministrateur(administrateurs.get(index));
                planification.setSalle(salles.get(index));
                planificationRepository.save(planification);
            });
        }

        executorService.shutdown();
    }
    @Override
    public List<Examen> getAllExamens() {
        return (List<Examen>) examenRepository.findAll();
    }

    @Override
    public Optional<Examen> findById(Long id) {
        return examenRepository.findById(id);
    }

    @Override
    public void update(Examen examen) {
        examenRepository.save(examen);
    }

    @Override
    public void delete(long id) {
        examenRepository.deleteById(id);
    }

    @Override
    public boolean isExamenExists(Long elementId, String semester, String year, String type, String session) {
        return examenRepository.existsByElementPedagogiqueIdAndSemestreAndAnneeUniversitaireAndTypeAndSession(
                elementId, semester, year, type, session);
    }
}
