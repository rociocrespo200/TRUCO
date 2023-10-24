package com.tallerwebi.dominio;

import java.util.List;

public class Mano {
    private Usuario jugador;
    private List<Carta> cartasEnLaMano;
    public Mano(Usuario jugador, List<Carta> cartasEnLaMano) {
        this.cartasEnLaMano = cartasEnLaMano;
        this.jugador = jugador;

    }

    public Usuario getJugador() {
        return jugador;
    }

    public void setJugador(Usuario jugador) {
        this.jugador = jugador;
    }

    public List<Carta> getCartasEnLaMano() {
        return cartasEnLaMano;
    }

    public void setCartasEnLaMano(List<Carta> cartasEnLaMano) {
        this.cartasEnLaMano = cartasEnLaMano;
    }
}
