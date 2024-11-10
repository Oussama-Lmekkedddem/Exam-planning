package com.PE.springprj.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;



@Entity
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private int capacity;
    private String type;

    @OneToMany(mappedBy = "salle", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Planification> planifications = new HashSet<>();


    public Salle(Long id, String numero, int capacity, String type, Set<Planification> planifications) {
        this.id = id;
        this.numero = numero;
        this.capacity= capacity;
        this.type = type;
        this.planifications = planifications;
    }

    public Salle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Planification> getPlanifications() {
        return planifications;
    }

    public void setPlanifications(Set<Planification> planifications) {
        this.planifications = planifications;
    }
}
