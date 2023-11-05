package com.tallerwebi.dominio;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
    private List<Equipo> equipos;
    private List<Usuario> jugadores;

    private Long ganador;
    private List<Carta> baraja; //hay que pasarle la baraja desde la base de datos;
    private List<Mano> manoDelJugador;

    private List<Evento> eventos;

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
        eventos = new ArrayList<>();
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
            jugadasDeLaRonda.add(new Jugada(usuario.getId(), carta));
        }


        if(cartasEnLaMesa.size() == 2){
            terminarMano();
        }
        if(validarSiLaRondaTermino()){
            terminarRonda();
        }

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
                    asignarPuntosDeRonda();
                    break;
                };
                usuariosGanadores.add(jugada.getJugador());
            }
        }
    }

    private void asignarPuntosDeRonda() {
        for (Equipo equipo : equipos) {
            if(equipo.buscarJugador(ganador) != null){
                //si existe un evento de tipo truco obtener el puntaje y pasarsslo por parametos
                equipo.sumarPuntos(1);
            }
        }
    }

    public boolean validarSiLaRondaTermino2() {
        List<Long> usuariosGanadores = new ArrayList<>();

        for(Jugada jugada : jugadasDeLaRonda){
            if(jugada.getJugadaGanadora()){
                if(usuariosGanadores.contains(jugada.getJugador())) return true;
                usuariosGanadores.add(jugada.getJugador());
            }
        }

        return false;
    }

    public boolean validarSiLaRondaTermino() {
        return jugadasDeLaRonda.size() == 6;
    }


    //-------------- MANO --------------
    public void terminarMano(){
        calcularGanadorMano();
        cartasEnLaMesa.clear();

    }

    private void calcularGanadorMano() {
        Jugada jugada1 = cartasEnLaMesa.get(0);
        Jugada jugada2 = cartasEnLaMesa.get(1);

        if(jugada1.getCarta().getValor() < jugada2.getCarta().getValor()) {
            buscarJugada(jugada2).setJugadaGanadora(true);
        }
        else{
            buscarJugada(jugada1).setJugadaGanadora(true);
        }
    }

    private Jugada buscarJugada(Jugada jugada2) {
        for(Jugada jugada : jugadasDeLaRonda){
            if(jugada.getCarta() == jugada2.getCarta()){
                return jugada;
            }
        }
        return null;
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
        return (jugadasDeLaRonda.size() != 1) ? jugadasDeLaRonda.get(jugadasDeLaRonda.size()-1) : jugadasDeLaRonda.get(0);
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

    public Long getGanador() {
        return ganador;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void registroEvento(Evento evento) {
        eventos.add(evento);
    }

    public Evento obtenerUltimoEvento() {
        return eventos.get(eventos.size()-1);
    }
}
