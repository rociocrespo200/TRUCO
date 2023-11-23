package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.presentacion.DatosEvento;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("repositorioPartida")
public class RepositorioPartidaImpl implements RepositorioPartida {


    private SessionFactory sessionFactory;

    private Partida partida;

    @Autowired
    public RepositorioPartidaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;

    }


    @Override
    public void crearPartida(ArrayList<Long> usuariosConectados) {

        Usuario jugador1 = buscarJugador(usuariosConectados.get(0));
        Usuario jugador2 = buscarJugador(usuariosConectados.get(1));

        partida = new Partida();
        partida.agregarEquipo(jugador1, jugador2);

        iniciarRonda();

    }

    public Usuario buscarJugador(Long aLong) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("id", aLong))
                .uniqueResult();}

    public boolean verficarSiLaRondaEstaIniciado(){
        return partida.verficarSiLaRondaEstaIniciado();
    }

    @Override
    public void iniciarRonda() {

        partida.iniciarRonda(obtenerBaraja());
    }


    @Override
    public Jugada obtenerUltimaJugada() {
        return partida.obtenerRondaActual().obtenerUltimaJugada();
    }

    @Override
    public List<Equipo> obtenerJugadoresEnLaPartida() {
        return partida.getEquipos();
    }

    @Override
    public boolean validarSiTerminoRonda() {
        if(partida.obtenerRondaActual().validarSiLaRondaTermino()){
        //  Long ganador = partida.obtenerRondaActual().getGanador();
        //  partida.asignarPuntaje(partida.obtenerRondaActual().getEquipos());

            for (Equipo equipo: partida.obtenerRondaActual().getEquipos()){
                if(equipo.getPuntos()>=15){
                    partida.setEquipoGanador(equipo);
                    sumarPuntosRankingAGanador(equipo);
                }
            }

            partida.iniciarRonda(obtenerBaraja());
            return true;
        }
        return false;
    }
    public void sumarPuntosRankingAGanador(Equipo equipo){
        Usuario usuario= equipo.getJugadores().get(0);
        usuario.setPuntos_ranking(usuario.getPuntos_ranking()+5);
        modificarUsuario(usuario);
    }
    @Override
    public void modificarUsuario(Usuario usuario){

        sessionFactory.getCurrentSession().update(usuario);

    }

    @Override
    public Equipo obtenerGanadorDeLaPartida() {
        return partida.getEquipoGanador();
    }

    public List<Carta> obtenerBaraja() {
        return sessionFactory.getCurrentSession().createQuery("FROM Carta", Carta.class).list();
    }

    public List<Usuario> obtenerUsuario() {
        return sessionFactory.getCurrentSession().createQuery("FROM Usuario", Usuario.class).list();
    }

    public Carta obtenerCarta(Integer id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Carta) session.createCriteria(Carta.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
   }

    public List<Carta> obtenerManoDelJugador(Long usuario){
        List<Mano> manos = partida.obtenerRondaActual().getManosDeLosJugadores();

        for (int i = 0; i < manos.size(); i++) {
            if (manos.get(i).getJugador().getId().equals(usuario)){
                List<Carta> cartasEnLaMano = manos.get(i).getCartasEnLaMano();
                return cartasEnLaMano;
            }
        }
        return null;
    }

    @Override
    public Integer obtenerCantidadDeJugadores() {
        return partida.obtenerCantidadDeJugadores();
    }

    @Override
    public void jugarCarta(Long usuarioId, Integer idCarta) {
        Usuario usuario = partida.buscarUsuarioPorId(usuarioId);
        Carta carta = obtenerCarta(idCarta);
        partida.obtenerRondaActual().jugarCarta(usuario, carta);
    }


    public void registrarEvento(DatosEvento eventoDB) {


        List<String> eventos = partida.obtenerRondaActual().getEventosTemp();
        String eventoConcatenado = concatenarEventos(eventos);
        Evento evento = obtenerEvento(eventoConcatenado);
        partida.obtenerRondaActual().registroEvento(evento,eventoDB.getUsuario());

/*
        if (eventoDB.getFinalizado()){
            partida.obtenerRondaActual().registroEvento(evento);
        }else{
            CalcularAsignaciondePuntosCasosNegativos(eventoDB);
        }

        if (eventoDB.getNombre().contains("IRSE_AL_MAZO")){
            CalcularAsignaciondePuntosCasosNegativos(eventoDB);
        }*/


    }

    private void CalcularAsignaciondePuntosCasosNegativos(DatosEvento eventoDB) {
        if (!AqueEquipoPertenece(partida.getEquipos().get(0),eventoDB)){
            partida.asignarPuntajeaunequipo(partida.getEquipos().get(0),obtenerEvento(eventoDB.getNombre()).getValor());
        }
        if (!AqueEquipoPertenece(partida.getEquipos().get(1),eventoDB)){
            partida.asignarPuntajeaunequipo(partida.getEquipos().get(1),obtenerEvento(eventoDB.getNombre()).getValor());

        }
    }

    private boolean AqueEquipoPertenece(Equipo equipo, DatosEvento eventoDB) {
        if (equipo.getJugadores().get(0).getId().equals(eventoDB.getUsuario())){
            return true;
        }

        return false;
    }


    private String concatenarEventos(List<String> eventos) {
        StringBuilder nombreCompuestoDelEvento = new StringBuilder();
        for (int i = 0; i < eventos.size(); i++) {
            if (i != eventos.size() - 1) {
                nombreCompuestoDelEvento.append(eventos.get(i)).append(" ");
            } else {
                nombreCompuestoDelEvento.append(eventos.get(i));
            }
        }
        return nombreCompuestoDelEvento.toString();

    }

    @Override
    public String obtenerUltimoEvento() {
        return partida.obtenerRondaActual().obtenerUltimoEvento();
    }

    @Override
    public void guardarListaEvento(DatosEvento evento) {
        partida.obtenerRondaActual().guardarListaEvento(evento.getNombre());
    }

    private Evento obtenerEvento(String nombre) {
        final Session session = sessionFactory.getCurrentSession();
        return (Evento) session.createCriteria(Evento.class)
                .add(Restrictions.eq("nombre", nombre))
                .uniqueResult();
    }
}
