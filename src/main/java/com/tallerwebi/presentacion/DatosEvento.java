package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Usuario;

public class DatosEvento {

    private Integer usuario;
    private String nombre;
    private Boolean finalizado;

    public DatosEvento(){

    }

    public DatosEvento(Integer usuario, String nombre, Boolean finalizado) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.finalizado = finalizado;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
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
