package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private boolean quiero;
    private int valor;

    // Constructor vacío
    public Evento() {
    }

    // Constructor con todos los campos
    public Evento(String nombre, boolean quiero, int valor) {
        this.nombre = nombre;
        this.quiero = quiero;
        this.valor = valor;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isQuiero() {
        return quiero;
    }

    public void setQuiero(boolean quiero) {
        this.quiero = quiero;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
