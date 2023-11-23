package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosEvento;

import java.util.ArrayList;
import java.util.List;

public interface RepositorioPartida {


    void crearPartida(ArrayList<Long> usuariosConectados);
    List<Carta> obtenerManoDelJugador(Long usuario);

    Integer obtenerCantidadDeJugadores();

    void jugarCarta(Long usuario, Integer carta);

    boolean verficarSiLaRondaEstaIniciado();

    void iniciarRonda();

    void modificarUsuario(Usuario usuario);

    Equipo obtenerGanadorDeLaPartida();

    Jugada obtenerUltimaJugada();

    List<Equipo> obtenerJugadoresEnLaPartida();

    boolean validarSiTerminoRonda();

    void registrarEvento(DatosEvento eventoDB);

    String obtenerUltimoEvento();

    void guardarListaEvento(DatosEvento evento);
}
