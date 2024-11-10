package com.PE.springprj.entities;

import javax.persistence.*;


@Entity
public class TempsLibre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_enseignant", nullable = false)
    private Enseignant enseignant;

    @Column(name = "interval_libre")
    private String intervalLibre;

    @Column(name = "jour_semaine", length = 10)
    private String jourSemaine;


    @Column(name = "interval_exite")
    private Boolean intervalExiste;


    public TempsLibre() {}

    public TempsLibre(Enseignant enseignant, String jourSemaine, String intervalLibre) {
        this.enseignant = enseignant;
        this.jourSemaine = jourSemaine;
        this.intervalLibre = intervalLibre;
    }

/*
    public TempsLibre(Long id, Enseignant enseignant, String intervalLibre, String jourSemaine, Boolean intervalReserver) {
        this.id = id;
        this.enseignant = enseignant;
        this.intervalLibre = intervalLibre;
        this.jourSemaine = jourSemaine;
        this.intervalReserver = intervalReserver;
    }
 */
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

    public String getIntervalLibre() {
        return intervalLibre;
    }

    public void setIntervalLibre(String intervalLibre) {
        this.intervalLibre = intervalLibre;
    }

    public String getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(String jourSemaine) {
        this.jourSemaine = jourSemaine;
    }


    public Boolean getIntervalExiste() {
        return intervalExiste;
    }

    public void setIntervalExiste(Boolean intervalExiste) {
        this.intervalExiste = intervalExiste;
    }


}
