package com.PE.springprj.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class ElementPedagogique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    private String titre;

    @NotNull(message = "Level is required")
    private String niveau;

    @NotNull(message = "Type is required")
    private String type;

    private String filiere;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_enseignant")
    private Enseignant enseignant;


    @OneToMany(mappedBy ="elementPedagogique", cascade = CascadeType.ALL)
    private Set<Examen> examens;
    // Constructeurs, getters et setters

    public ElementPedagogique() {
    }

    public ElementPedagogique(Long id, String titre, String niveau, String type, String filiere) {
        this.id = id;
        this.titre = titre;
        this.niveau = niveau;
        this.type = type;
        this.filiere = filiere;
    }

    public ElementPedagogique(Long id, String titre, String niveau, String type, String filiere, Enseignant enseignant, Set<Examen> examens) {
        this.id = id;
        this.titre = titre;
        this.niveau = niveau;
        this.type = type;
        this.filiere = filiere;
        this.enseignant = enseignant;
        this.examens = examens;
    }

    // Getters et setters pour tous les attributs
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Set<Examen> getExamens() {
        return examens;
    }

    public void setExamens(Set<Examen> examens) {
        this.examens = examens;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getFiliere() {
        return filiere;
    }
}
