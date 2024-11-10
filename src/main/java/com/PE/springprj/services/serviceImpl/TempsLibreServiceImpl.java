package com.PE.springprj.services.serviceImpl;

import com.PE.springprj.entities.Enseignant;
import com.PE.springprj.entities.TempsLibre;
import com.PE.springprj.repositories.TempsLibreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PE.springprj.services.TempsLibreService;

@Service
public class TempsLibreServiceImpl implements TempsLibreService {

    @Autowired
    private TempsLibreRepository tempsLibreRepository;

    @Override
    public void save(TempsLibre tempsLibre) {
        tempsLibreRepository.save(tempsLibre);
    }


    @Override
    public void update(TempsLibre tempsLibre) {
        tempsLibreRepository.save(tempsLibre);
    }

    @Override
    public void delete(long id) {
        tempsLibreRepository.deleteById(id);
    }


    @Override
    public void initTempsLibreForEnseignant(Enseignant enseignant) {
        String[] joursSemaine = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] intervalles = {"08:00 - 10:00", "10:00 - 12:00", "14:00 - 16:00", "16:00 - 18:00"};
        for (String jour : joursSemaine) {
            for (String intervalle : intervalles) {
                TempsLibre tempsLibre = new TempsLibre();
                tempsLibre.setJourSemaine(jour);
                tempsLibre.setIntervalLibre(intervalle);
                tempsLibre.setIntervalExiste(true);
                tempsLibre.setEnseignant(enseignant);
                tempsLibreRepository.save(tempsLibre);
            }
        }
    }

    @Override
    public void updateIntervalExiste(Long tempsLibreId, Boolean intervalExiste) {
        tempsLibreRepository.updateIntervalExisteById(tempsLibreId, intervalExiste);
    }

}
