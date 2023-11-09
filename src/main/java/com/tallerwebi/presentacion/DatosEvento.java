package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Usuario;

public class DatosEvento {

    private Long usuario;
    private String nombre;
    private Boolean finalizado;

    public DatosEvento(){

    }

    public DatosEvento(Long usuario, String nombre, Boolean finalizado) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.finalizado = finalizado;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }
}
