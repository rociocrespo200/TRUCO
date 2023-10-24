package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    private List<Equipo> equipos;
    private List<Usuario> jugadores;
    private List<Carta> baraja; //hay que pasarle la baraja desde la base de datos;
    private List<Mano> manoDelJugador;
    private List<Jugada> cartasEnLaMesa;


    public Ronda(List<Equipo> equipos, List<Usuario> jugadores, List<Carta> baraja) {
        this.equipos = equipos;
        this.jugadores = jugadores;
        this.baraja = baraja;
        manoDelJugador = new ArrayList<>();
        cartasEnLaMesa = new ArrayList<>();
        //repartir();
    }


    public void repartir() {

        List<Carta> cartas = new ArrayList<>();
        cartas.addAll(baraja);

        for (int i = 0; i < jugadores.size(); i++) {
            List<Carta> cartasAleatorias = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                int indiceRandom = (int) (Math.random() * cartas.size());
                Carta cartaAleatoria = cartas.get(indiceRandom);

                cartasAleatorias.add(cartaAleatoria);
                cartas.remove(cartaAleatoria);
            }
            manoDelJugador.add(new Mano (jugadores.get(i), cartasAleatorias));
        }
    }

    public void jugarCarta(Usuario usuario, Carta carta){
        cartasEnLaMesa.add(new Jugada(usuario.getId(), carta));

    }

    private boolean validarSiYaTiroCarta(Usuario usuario) {
        for (int i = 0; i < manoDelJugador.size(); i++) {
           if( manoDelJugador.get(i).getJugador() == usuario) return true;
        }
        return false;
    }

    private boolean validarSiTerminoMano() {
        return cartasEnLaMesa.size() == jugadores.size() && !manoDelJugador.isEmpty();
    };

    public Jugada terminarMano(){
        Jugada jugadaGanadora = calcularGanador();
        //manoDelJugador.clear();
        //ordenarJugadores(ganador);
        jugadaGanadora.setJugadaGanadora(true);
        return jugadaGanadora;
    }

    private void ordenarJugadores(Usuario ganador) {
        int indiceGanador = jugadores.indexOf(ganador);

        if (indiceGanador >= 0) {
            List<Usuario> subListaAntes = jugadores.subList(0, indiceGanador);
            List<Usuario> subListaDespues = jugadores.subList(indiceGanador + 1, jugadores.size());

            jugadores.clear();

            jugadores.addAll(subListaDespues);
            jugadores.add(0, ganador);
            jugadores.addAll(subListaAntes);
        }
    }

    private Jugada calcularGanador() {
        Jugada jugadaGanadora = cartasEnLaMesa.get(0);

        for (int i = 1; i < cartasEnLaMesa.size(); i++) {
            if(cartasEnLaMesa.get(i).getCarta().getValor() > jugadaGanadora.getCarta().getValor()){
                jugadaGanadora = cartasEnLaMesa.get(i);
            }
        }

        return jugadaGanadora;
    }

    public List<Carta> getBaraja() {
        return baraja;
    }

    public List<Mano> getManosDeLosJugadores() {
        return manoDelJugador;
    }

    public List<Jugada> getCartasEnLaMesa() {
        return cartasEnLaMesa;
    }

    public boolean validarSiLaRondaTermino() {
        return cartasEnLaMesa.size() == jugadores.size() * 3;
    }

    public Jugada obtenerUltimaJugada() {
        if (validarSiTerminoMano()) {
            manoDelJugador.clear();
           return terminarMano();

        }
        return cartasEnLaMesa.get(cartasEnLaMesa.size()-1);
    }
}
