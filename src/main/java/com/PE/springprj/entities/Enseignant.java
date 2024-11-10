package com.PE.springprj.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Enseignant extends Personnel {

    @NotNull(message = "Filiere is required")
    private String filiere;

    @NotNull(message = "Departement is required")
    private String departement;

    @OneToMany(mappedBy ="enseignant", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Examen> examens;

    @ManyToMany(mappedBy ="enseignants", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Group> groups;

    @OneToMany(mappedBy = "enseignant", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<ElementPedagogique>elements;

    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL)
    private Set<TempsLibre> tempsLibres = new HashSet<>();

    @OneToMany(mappedBy = "enseignant", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Planification> planifications = new HashSet<>();


    public Enseignant() {
        super();
    }

    public Enseignant(String filiere, String departement, Set<Examen> examens, Set<Group> groups, Set<ElementPedagogique> elements, Set<TempsLibre> tempsLibres, Set<Planification> planifications) {
        this.filiere = filiere;
        this.departement = departement;
        this.examens = examens;
        this.groups = groups;
        this.elements = elements;
        this.tempsLibres = tempsLibres;
        this.planifications = planifications;
    }

    public Enseignant(String nom, String prenom, String filiere, String departement, Set<Examen> examens, Set<Group> groups, Set<ElementPedagogique> elements, Set<TempsLibre> tempsLibres, Set<Planification> planifications) {
        super(nom, prenom);
        this.filiere = filiere;
        this.departement = departement;
        this.examens = examens;
        this.groups = groups;
        this.elements = elements;
        this.tempsLibres = tempsLibres;
        this.planifications = planifications;
    }

    public Set<Examen> getExamens() {
        return examens;
    }

    public void setExamens(Set<Examen> examens) {
        this.examens = examens;
    }

    public Set<Planification> getPlanifications() {
        return planifications;
    }

    public void setPlanifications(Set<Planification> planifications) {
        this.planifications = planifications;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public Set<ElementPedagogique> getElements() {
        return elements;
    }

    public void setElements(Set<ElementPedagogique> elements) {
        this.elements = elements;
    }

    public Set<TempsLibre> getTempsLibres() {
        return tempsLibres;
    }

    public void setTempsLibres(Set<TempsLibre> tempsLibres) {
        this.tempsLibres = tempsLibres;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

}