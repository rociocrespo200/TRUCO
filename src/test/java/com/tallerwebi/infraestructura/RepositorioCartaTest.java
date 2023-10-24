//package com.tallerwebi.infraestructura;
//import com.tallerwebi.dominio.*;
//import com.tallerwebi.integracion.config.HibernateTestConfig;
//import com.tallerwebi.integracion.config.SpringWebTestConfig;
//import com.tallerwebi.presentacion.ControladorLogin;
//import com.tallerwebi.presentacion.DatosLogin;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
//public class RepositorioCartaTest {
//
//    @Autowired
//    private RepositorioPartidaImpl repositorioPartida;
//
//    @BeforeEach
//    public void init(){
//        repositorioPartida =  mock(RepositorioPartidaImpl.class);
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback
//    public void testObtenerCartas() {
//        assertEquals(40, repositorioPartida.obtenerBaraja().size());
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback
//    public void testObtenerUsuarios() {
//        assertEquals(2, repositorioPartida.obtenerUsuario().size());
//    }
//    @Test
//    @Transactional
//    @Rollback
//    public void testQueNoSeRepartanCartasRepetidas() {
//        List<Equipo> equipos = new ArrayList<>();
//        List<Usuario> jugadores = new ArrayList<>();
//        List<Carta> baraja = new ArrayList<>();
//
//
//        Usuario jugador1 = new Usuario();
//        Usuario jugador2 = new Usuario();
//
//        jugador1.setId(1L);
//        jugador2.setId(2L);
//
//        jugadores.add(jugador1);
//        jugadores.add(jugador2);
//
//        baraja.addAll(repositorioPartida.obtenerBaraja());
//
//        for (int i = 0; i < 2000; i++){
//            Ronda ronda = new Ronda(equipos,jugadores,baraja);
//
//            ronda.repartir();
//
//            List<Mano> manosDeLosJugadores = ronda.getManosDeLosJugadores();
//
//            manosDeLosJugadores.get(0).getCartasEnLaMano().get(0);
//
//            HashSet<Integer> cartas = new HashSet<>();
//
//            cartas.add(manosDeLosJugadores.get(0).getCartasEnLaMano().get(0).hashCode());
//            cartas.add(manosDeLosJugadores.get(0).getCartasEnLaMano().get(1).hashCode());
//            cartas.add(manosDeLosJugadores.get(0).getCartasEnLaMano().get(2).hashCode());
//            cartas.add(manosDeLosJugadores.get(1).getCartasEnLaMano().get(0).hashCode());
//            cartas.add(manosDeLosJugadores.get(1).getCartasEnLaMano().get(1).hashCode());
//            cartas.add(manosDeLosJugadores.get(1).getCartasEnLaMano().get(2).hashCode());
//
//            assertEquals(6, cartas.size());
//        }
//
//    }
//}
//
//
//
//
//
//
//
