package com.PE.springprj.repositories;

import com.PE.springprj.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository  extends JpaRepository<Salle, Long> {
}
