package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioSala {

    Sala buscarsala(String nombre);

    boolean Salaexistente(String nombre);

    boolean guardarSala(Sala sala);

    List<Sala> obtenersalas();

    void modificarSala(Sala sala);

    void eliminarsala(Sala sala);
}
