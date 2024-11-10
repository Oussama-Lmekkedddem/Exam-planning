package com.PE.springprj.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Administrateur extends Personnel {

    @OneToMany(mappedBy = "administrateur", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Planification> planifications = new HashSet<>();

    // Constructeurs
    public Administrateur() {
        super();
    }

    public Administrateur(String nom, String prenom) {
        super(nom, prenom);
    }

    public Set<Planification> getPlanifications() {
        return planifications;
    }

    public void setPlanifications(Set<Planification> planifications) {
        this.planifications = planifications;
    }
}