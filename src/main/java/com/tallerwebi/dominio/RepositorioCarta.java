package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioCarta {
    List<Carta> obtenerCartas();
    void generarPalos();
    List<Palo> obtenerTodosLosPalos();
    void generarCartas();
}
