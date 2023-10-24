package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.ControladorSala;
import org.hsqldb.lib.HsqlArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.tallerwebi.dominio.ServicioSala;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioSalaTest {


    private ControladorSala controladorSala;
    private Sala sala;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioSalaImpl servicioSala;
    private ServicioSala servicioSalamock;
    private RepositorioSala repositoriomock;
    @BeforeEach
    public void init(){

        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        repositoriomock = mock(RepositorioSala.class);
        servicioSala = new ServicioSalaImpl(repositoriomock);
        servicioSalamock = mock(ServicioSala.class);
        sala = new Sala("pepe",2);

    }

    @Test
    public void QueObtengaunaSalaPorID(){

        when(servicioSalamock.obtenersala(anyString())).thenReturn(sala);


        assertEquals(sala.getNombre_sala(),servicioSalamock.obtenersala(sala.getNombre_sala()).getNombre_sala());

    }
    @Test
    public void QueCreeunaSalaRecibiendounaSala(){
        sala.setNombre_sala("pepe");
        when(servicioSalamock.obtenersala(anyString())).thenReturn(sala);

        servicioSalamock.crearsala(sala);

        assertEquals(sala.getNombre_sala(),servicioSalamock.obtenersala(sala.getNombre_sala()).getNombre_sala());

    }

    @Test
    public void QueObtengaunaListadeSalas(){


        Sala sala2 = new Sala(1);
        ArrayList<Sala> salas=  new ArrayList<Sala>();
        salas.add(sala);
        salas.add(sala2);
        when(servicioSalamock.obtenerlistadeSalas()).thenReturn(salas);
        servicioSalamock.crearsala(sala);

        assertEquals(2,servicioSalamock.obtenerlistadeSalas().size());

    }







}