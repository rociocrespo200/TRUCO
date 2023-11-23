package com.tallerwebi.dominio;

import java.util.List;

public class Mano {
    private Usuario jugador;
    private List<Carta> cartasEnLaMano;

    private Integer tanto;
    public Mano(Usuario jugador, List<Carta> cartasEnLaMano , Integer tanto) {
        this.cartasEnLaMano = cartasEnLaMano;
        this.jugador = jugador;
        this.tanto=tanto;
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

    public Integer getTanto() {
        return tanto;
    }

    public void setTanto(Integer tanto) {
        this.tanto = tanto;
    }

}
