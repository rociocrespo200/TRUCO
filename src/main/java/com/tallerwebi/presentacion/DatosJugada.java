package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Carta;
import com.tallerwebi.dominio.Mano;

import javax.activation.CommandObject;
import java.util.ArrayList;
import java.util.List;

public class DatosJugada {

    private Long jugador;
    private Integer carta;

    private List<Mano> manos;

    public DatosJugada(){

    }
    public DatosJugada(Long jugador, Integer carta) {
        this.jugador = jugador;
        this.carta = carta;
        manos = new ArrayList<>();
    }

    public Long getJugador() {
        return jugador;
    }

    public void setJugador(Long jugador) {
        this.jugador = jugador;
    }

    public Integer getCarta() {
        return carta;
    }

    public void setCarta(Integer carta) {
        this.carta = carta;
    }
}
