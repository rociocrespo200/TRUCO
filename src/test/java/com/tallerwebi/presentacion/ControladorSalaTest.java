package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.RepositorioSala;
import com.tallerwebi.dominio.ServicioSala;
import com.tallerwebi.dominio.Sala;

import org.hamcrest.Matchers;
import org.hamcrest.core.AnyOf;
import org.hibernate.SessionFactory;
import org.hibernate.type.AnyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorSalaTest {

    private ControladorSala controladorSala;
    private Sala sala;
    private Sala sala2;

    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioSala servicioSalaMock;

    private RepositorioSala repositoriomock;

    private ArrayList<Sala> salas;
    @BeforeEach
    public void init(){
        sala = mock(Sala.class);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        servicioSalaMock = mock(ServicioSala.class);
        controladorSala = new ControladorSala(servicioSalaMock);
        salas = new ArrayList<Sala>();
        salas.add(sala);
        sala2= mock(Sala.class);
        salas.add(sala2);

    }

    @Test
    public void QueDevuelvaUnaListaDeSalas(){

        when(servicioSalaMock.obtenerlistadeSalas()).thenReturn(salas);

        ModelAndView modelAndView = controladorSala.irASalas();
        ArrayList<Sala> modelo= (ArrayList<Sala>) modelAndView.getModel().get("ListadeSalas");
        assertEquals(modelo.size(),2);

    }

    @Test
    public void QueNoDevuelvaUnaListaDeSalas(){

        ModelAndView modelAndView = controladorSala.irASalas();
        assertEquals(modelAndView.getModel().get("ninguna_sala").toString(),"No hay ninguna sala");
        assertEquals(modelAndView.getViewName(),"salas");
    }

/*

    @Test
    public void QuealIngresarASalaRedireccioneaIniciarPartida(){

        when(servicioSalaMock.obtenersala(1L)).thenReturn(sala);

        ModelAndView modelAndView = controladorSala.IngresaraSala(1L);
        assertEquals(modelAndView.getViewName(),"redirect:/partida");

    }


    @Test
    public void QueRedireccioneaPartidaAlCrearUnaSala(){

        ModelAndView modelAndView = controladorSala.crearsala();
        assertEquals(modelAndView.getViewName(),"redirect:/iniciarPartida");

    }

    @Test
    public void QueAlCrearunaSalaRedireccioneaLaPartida(){

       // when(servicioSalaMock.crearsala(ArgumentMatchers.any(Sala.class))).thenReturn(true);

        when(servicioSalaMock.crearsala(any(Sala.class))).thenReturn(true);

        ModelAndView modelAndView = controladorSala.creadordesala("2", new ModelAndView() );
        assertEquals(modelAndView.getViewName(),"redirect:/partida");

    }

    @Test
    public void QueAlNoCrearunaSalaTireError(){

        ModelAndView modelAndView = controladorSala.creadordesala("2", new ModelAndView() );
        assertEquals(modelAndView.getModel().get("error_crear_sala").toString(),"Error al crear la sala");

    }
    */

}
