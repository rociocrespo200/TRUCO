package com.tallerwebi.dominio;

public class Jugada {

    private Long jugador;
    private Carta carta;

    private Boolean jugadaGanadora;

    public Jugada() {
    }

    public Jugada(Long jugador, Carta carta) {
        this.jugador = jugador;
        this.carta = carta;
        jugadaGanadora = false;
    }

    public Long getJugador() {
        return jugador;
    }

    public void setJugador(Long jugador) {
        this.jugador = jugador;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Boolean getJugadaGanadora() {
        return jugadaGanadora;
    }

    public void setJugadaGanadora(Boolean jugadaGanadora) {
        this.jugadaGanadora = jugadaGanadora;
    }
}
