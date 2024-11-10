package com.PE.springprj.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First Name is required")
    private String nom;

    @NotNull(message = "Last Name is required")
    private String prenom;

    // Constructeurs, getters et setters

    public Personnel() {
    }

    public Personnel(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}