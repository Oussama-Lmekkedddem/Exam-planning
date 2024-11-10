package com.PE.springprj.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Planification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="enseignant_id")
    private Enseignant enseignant;
    @ManyToOne
    @JoinColumn(name="administrateur_id")
    private Administrateur administrateur;
    @ManyToOne
    @JoinColumn(name="examen_id")
    private Examen examen;
    @ManyToOne
    @JoinColumn(name="salle_id")
    private Salle salle;

    public Planification() {
    }

    public Planification(Long id, Enseignant enseignant, Administrateur administrateur, Examen examen, Salle salle) {
        this.id = id;
        this.enseignant = enseignant;
        this.administrateur = administrateur;
        this.examen = examen;
        this.salle = salle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}