package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioSala {

    boolean crearsala(Sala sala);


    Sala obtenersala(String nombre);

    List<Sala> obtenerlistadeSalas();

    void modificarsala(Sala sala);

    void eliminarsala(Sala sala);
}
