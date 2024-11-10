package com.PE.springprj.repositories;


import com.PE.springprj.entities.Enseignant;
import com.PE.springprj.entities.TempsLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TempsLibreRepository extends JpaRepository<TempsLibre, Long> {

    List<TempsLibre> findByEnseignantIdAndJourSemaineAndIntervalLibre(Long enseignantId, String jourSemaine, String intervalLibre);

    List<TempsLibre> findByEnseignantIdOrderByIdAsc(Long enseignantId);

    @Modifying
    @Transactional
    @Query("UPDATE TempsLibre t SET t.intervalExiste = :intervalExiste WHERE t.id = :tempsLibreId")
    void updateIntervalExisteById(Long tempsLibreId, Boolean intervalExiste);

    @Query("SELECT DISTINCT t.enseignant FROM TempsLibre t WHERE t.jourSemaine = :day AND t.intervalLibre =:time AND t.intervalExiste = true")
    List<Enseignant> findEnseignantsByDayByTime(@Param("day") String day, @Param("time") String time);

}
