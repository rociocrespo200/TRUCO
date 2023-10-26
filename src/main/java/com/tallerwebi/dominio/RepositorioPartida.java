package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.Evento;

import java.util.ArrayList;
import java.util.List;

public interface RepositorioPartida {


    void crearPartida(ArrayList<Long> usuariosConectados);
    List<Carta> obtenerManoDelJugador(Long usuario);

    Integer obtenerCantidadDeJugadores();

    void jugarCarta(Long usuario, Integer carta);

    boolean verficarSiLaRondaEstaIniciado();

    void iniciarRonda();

    Jugada obtenerUltimaJugada();

    List<Usuario> obtenerJugadoresEnLaPartida();

    void registrarevento(Evento evento);

    void noquiere(Evento evento, Usuario usuario);

    void almazo(Usuario usuario);
}
