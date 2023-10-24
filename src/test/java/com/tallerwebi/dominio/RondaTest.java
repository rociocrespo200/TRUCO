package com.tallerwebi.dominio;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RondaTest {

    @Test
    public void testQueNoSeRepartanCartasRepetidas() {
        List<Equipo> equipos = new ArrayList<>();
        List<Usuario> jugadores = new ArrayList<>();
        List<Carta> baraja = new ArrayList<>();

        Palo oro = new Palo("oro");
        Palo espada = new Palo("espada");
        Palo copa = new Palo("copa");


        Usuario jugador1 = new Usuario();
        Usuario jugador2 = new Usuario();

        jugador1.setId(1L);
        jugador2.setId(2L);

        jugadores.add(jugador1);
        jugadores.add(jugador2);

        baraja.add(new Carta(1, oro, 12, "imagen1.png"));
        baraja.add(new Carta(4, oro, 12, "imagen1.png"));
        baraja.add(new Carta(11, oro, 12, "imagen1.png"));
        baraja.add(new Carta(1, espada, 12, "imagen1.png"));
        baraja.add(new Carta(4, espada, 12, "imagen1.png"));
        baraja.add(new Carta(11, espada, 12, "imagen1.png"));
        baraja.add(new Carta(1, copa, 12, "imagen1.png"));
        baraja.add(new Carta(4, copa, 12, "imagen1.png"));
        baraja.add(new Carta(11, copa, 12, "imagen1.png"));



        for (int i = 0; i < 2000; i++){
            Ronda ronda = new Ronda(equipos,jugadores,baraja);

            ronda.repartir();

            List<Mano> manosDeLosJugadores = ronda.getManosDeLosJugadores();

            manosDeLosJugadores.get(0).getCartasEnLaMano().get(0);

            HashSet<Integer> cartas = new HashSet<>();

            cartas.add(manosDeLosJugadores.get(0).getCartasEnLaMano().get(0).hashCode());
            cartas.add(manosDeLosJugadores.get(0).getCartasEnLaMano().get(1).hashCode());
            cartas.add(manosDeLosJugadores.get(0).getCartasEnLaMano().get(2).hashCode());
            cartas.add(manosDeLosJugadores.get(1).getCartasEnLaMano().get(0).hashCode());
            cartas.add(manosDeLosJugadores.get(1).getCartasEnLaMano().get(1).hashCode());
            cartas.add(manosDeLosJugadores.get(1).getCartasEnLaMano().get(2).hashCode());

            assertEquals(6, cartas.size());
        }

    }
}
