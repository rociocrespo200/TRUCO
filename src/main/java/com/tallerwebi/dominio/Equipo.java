package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo {
    private int id;

    private int puntos;

    private List<Usuario> jugadores;

    public Equipo(int id, Usuario jugador) {
        this.id = id;
        this.puntos = 0;
        jugadores = new ArrayList<>();
        jugadores.add(jugador);
    }



    public int getId() {
        return id;
    }

    public int getPuntos() {
        return puntos;
    }

    public List<Usuario> getJugadores() {
        return jugadores;


    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void setJugadores(List<Usuario> jugadores) {
        this.jugadores = jugadores;
    }

    public void agregarJugador(Usuario jugador) {
        jugadores.add(jugador);
    }

    public void sumarPuntos(int i) {
        this.puntos = this.puntos + i;
    }

    public Usuario buscarJugador(Long ganador) {

        for (Usuario usuario: jugadores){
            if(usuario.getId().equals(ganador)){
                return usuario;
            }
        }
        return null;
    }

}
