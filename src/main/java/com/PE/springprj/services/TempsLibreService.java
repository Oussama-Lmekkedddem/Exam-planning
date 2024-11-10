package com.PE.springprj.services;
import com.PE.springprj.entities.Enseignant;
import com.PE.springprj.entities.TempsLibre;

public interface TempsLibreService {
    void save(TempsLibre tempsLibre);

    void update(TempsLibre tempsLibre);

    void delete(long id);

    void initTempsLibreForEnseignant(Enseignant enseignant);

    void updateIntervalExiste(Long tempsLibreId, Boolean intervalExiste);

}
