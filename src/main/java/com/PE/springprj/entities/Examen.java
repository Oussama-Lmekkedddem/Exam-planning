package com.PE.springprj.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_ElementPedagogique")
    private ElementPedagogique elementPedagogique;

    @ManyToOne
    @JoinColumn(name="id_Enseignant")
    private Enseignant enseignant;


    private String semestre;
    private String session;
    private String type;
    private String date;
    private String heureDepart;
    private String dureePrevue;
    private String dureeReelle;
    private String anneeUniversitaire;
    private String pvFileName;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] pv;

    private String epreuveFileName;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] epreuve;

    @Column(columnDefinition = "TEXT")
    private String rapport;


    @OneToMany(mappedBy = "examen", cascade = CascadeType.ALL)
    private Set<Planification> planifications = new HashSet<>();

    public Examen() {
    }

    public Examen(Long id, ElementPedagogique elementPedagogique, Enseignant enseignant, String semestre, String session, String type, String date, String heureDepart, String dureePrevue, String dureeReelle, String anneeUniversitaire, String pvFileName, byte[] pv, String epreuveFileName, byte[] epreuve, String rapport, Set<Planification> planifications) {
        this.id = id;
        this.elementPedagogique = elementPedagogique;
        this.enseignant = enseignant;
        this.semestre = semestre;
        this.session = session;
        this.type = type;
        this.date = date;
        this.heureDepart = heureDepart;
        this.dureePrevue = dureePrevue;
        this.dureeReelle = dureeReelle;
        this.anneeUniversitaire = anneeUniversitaire;
        this.pvFileName = pvFileName;
        this.pv = pv;
        this.epreuveFileName = epreuveFileName;
        this.epreuve = epreuve;
        this.rapport = rapport;
        this.planifications = planifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElementPedagogique getElementPedagogique() {
        return elementPedagogique;
    }

    public void setElementPedagogique(ElementPedagogique elementPedagogique) {
        this.elementPedagogique = elementPedagogique;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getDureePrevue() {
        return dureePrevue;
    }

    public void setDureePrevue(String dureePrevue) {
        this.dureePrevue = dureePrevue;
    }

    public String getDureeReelle() {
        return dureeReelle;
    }

    public void setDureeReelle(String dureeReelle) {
        this.dureeReelle = dureeReelle;
    }

    public String getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }


    public String getPvFileName() {
        return pvFileName;
    }

    public void setPvFileName(String pvFileName) {
        this.pvFileName = pvFileName;
    }

    public String getEpreuveFileName() {
        return epreuveFileName;
    }

    public void setEpreuveFileName(String epreuveFileName) {
        this.epreuveFileName = epreuveFileName;
    }

    public byte[] getPv() {
        return pv;
    }

    public void setPv(byte[] pv) {
        this.pv = pv;
    }

    public byte[] getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(byte[] epreuve) {
        this.epreuve = epreuve;
    }
    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public Set<Planification> getPlanifications() {
        return planifications;
    }

    public void setPlanifications(Set<Planification> planifications) {
        this.planifications = planifications;
    }
}