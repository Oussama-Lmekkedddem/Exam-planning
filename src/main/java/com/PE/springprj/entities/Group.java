package com.PE.springprj.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;


@Entity
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    private String nom;

    @NotNull(message = "Enseignants is required")
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "Enseignant_Group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "enseignant_id"))
    private Set<Enseignant> enseignants;
    
    public Group() {
    }

    public Group(Long id, String nom, Set<Enseignant> enseignants) {
        this.id = id;
        this.nom = nom;
        this.enseignants = enseignants;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Set<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }
}
