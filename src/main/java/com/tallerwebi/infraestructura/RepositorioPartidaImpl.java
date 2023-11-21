package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.presentacion.Evento;
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

        for (Equipo equipo: partida.getEquipos()){
            if(equipo.getPuntos()>=15){
                partida.setEquipoGanador(equipo);
            }
        }

        partida.iniciarRonda(obtenerBaraja());
    }

    @Override
    public Equipo obtenerGanadorDeLaPartida() {
        return partida.getEquipoGanador();
    }

    @Override
    public Jugada obtenerUltimaJugada() {
        return partida.obtenerRondaActual().obtenerUltimaJugada();
    }

    @Override
    public List<Usuario> obtenerJugadoresEnLaPartida() {
        return partida.obtenerJugadoresEnLaPartida();
    }

    @Override
    public void registrarevento(Evento evento) {

    }

    @Override
    public void noquiere(Evento evento, Usuario usuario) {

    }

    @Override
    public void almazo(Usuario usuario) {

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


}
