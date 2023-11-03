package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Usuario;

public class DatosEvento {

    private Integer usuario;
    private String nombre;
    private Boolean quiero;

    public DatosEvento(){

    }
    public DatosEvento(Integer usuario, String nombre, Boolean quiero) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.quiero = quiero;
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

    public Boolean getQuiero() {
        return quiero;
    }

    public void setQuiero(Boolean quiero) {
        this.quiero = quiero;
    }
}
