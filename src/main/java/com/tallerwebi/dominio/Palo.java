package com.tallerwebi.dominio;

import javax.persistence.*;

@Entity
@Table(name = "Palo")
public class Palo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "palo")
    private String palo;

    public Palo() {
        // Constructor vacío requerido por Hibernate
    }

    public Palo(String palo) {
        this.palo = palo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    @Override
    public String toString() {
        return "Palo [id=" + id + ", palo=" + palo + "]";
    }
}
