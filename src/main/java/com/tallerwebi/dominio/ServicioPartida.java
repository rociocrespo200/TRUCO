package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("servicioPartida")
@Transactional
public class ServicioPartida {



    private RepositorioPartida repositorioPartida;

    @Autowired
    public ServicioPartida(RepositorioPartida repositorioPartida){
        this.repositorioPartida = repositorioPartida;
    }


    public void crearPartida(ArrayList<Long> usuariosConectados){
        repositorioPartida.crearPartida(usuariosConectados);
    };

    public List<Carta> obtenerManoDelJugador(Long usuario){
        return repositorioPartida.obtenerManoDelJugador(usuario);
    };

    public Integer obtenerCantDeJugadores(){
        return repositorioPartida.obtenerCantidadDeJugadores();
    }

    public void jugarCarta(Long usuario, Integer carta) {
        repositorioPartida.jugarCarta(usuario, carta);
    }

    public boolean verficarSiLaRondaEstaIniciado() {
        return repositorioPartida.verficarSiLaRondaEstaIniciado();
    }

    public void iniciarRonda() {
        repositorioPartida.iniciarRonda();
    }

    public Jugada obtenerUltimaJugada() {
        return repositorioPartida.obtenerUltimaJugada();
    }

    public List<Equipo>  obtenerJugadoresEnLaPartida() {
        return repositorioPartida.obtenerJugadoresEnLaPartida();
    }

    public boolean validarSiTerminoRonda() {
        return repositorioPartida.validarSiTerminoRonda();
    }

    public void registrarEvento() { repositorioPartida.registrarEvento();
    }

    public String obtenerUltimoEvento() {return repositorioPartida.obtenerUltimoEvento();}

    public void guardarListaEvento(DatosEvento evento) { repositorioPartida.guardarListaEvento(evento);

    }
}
