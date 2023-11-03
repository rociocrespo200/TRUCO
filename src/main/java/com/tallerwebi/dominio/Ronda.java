package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    private List<Equipo> equipos;
    private List<Usuario> jugadores;

    private Long ganador;
    private List<Carta> baraja; //hay que pasarle la baraja desde la base de datos;
    private List<Mano> manoDelJugador;

    private List<Jugada> cartasEnLaMesa;
    private List<Jugada> jugadasDeLaRonda;

    //private List<Evento> eventos;


    public Ronda(List<Equipo> equipos, List<Usuario> jugadores, List<Carta> baraja) {
        this.equipos = equipos;
        this.jugadores = jugadores;
        this.baraja = baraja;
        this.ganador = null;
        manoDelJugador = new ArrayList<>();
        cartasEnLaMesa = new ArrayList<>();
        jugadasDeLaRonda = new ArrayList<>();
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

        if(cartasEnLaMesa.isEmpty() || obtenerUltimaJugada().getJugador() != usuario.getId()){
            cartasEnLaMesa.add(new Jugada(usuario.getId(), carta));
        }


//        if(cartasEnLaMesa.size() == 2){
//            terminarMano();
//        }
//        if(validarSiLaRondaTermino()){
//            terminarRonda();
//        }

    }

    //-------------- RONDA --------------
    public void terminarRonda(){
        calcularGanadorRonda();
        manoDelJugador.clear();
        repartir();
    }

    private void calcularGanadorRonda() {
        List<Long> usuariosGanadores = new ArrayList<>();

        for(Jugada jugada : jugadasDeLaRonda){
            if(jugada.getJugadaGanadora()){
                if(usuariosGanadores.contains(jugada.getJugador())) {
                    ganador = jugada.getJugador();
                    break;
                };
                usuariosGanadores.add(jugada.getJugador());
            }
        }
    }

    public boolean validarSiLaRondaTermino() {
        List<Long> usuariosGanadores = new ArrayList<>();

        for(Jugada jugada : jugadasDeLaRonda){
            if(jugada.getJugadaGanadora()){
                if(usuariosGanadores.contains(jugada.getJugador())) return true;
                usuariosGanadores.add(jugada.getJugador());
            }
        }

        return false;
    }


    //-------------- MANO --------------
    public void terminarMano(){
        calcularGanadorMano();
        jugadasDeLaRonda.addAll(cartasEnLaMesa);
        cartasEnLaMesa.clear();

    }

    private void calcularGanadorMano() {
        Jugada jugada1 = cartasEnLaMesa.get(0);
        Jugada jugada2 = cartasEnLaMesa.get(1);

        if(jugada1.getCarta().getValor() < jugada2.getCarta().getValor())  jugada2.setJugadaGanadora(true);
        else jugada1.setJugadaGanadora(true);
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


    public Jugada obtenerUltimaJugada() {
        return (cartasEnLaMesa.size() != 1) ? cartasEnLaMesa.get(cartasEnLaMesa.size()-1) : cartasEnLaMesa.get(0);
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
}
