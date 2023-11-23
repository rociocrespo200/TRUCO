package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioRanking {
    Usuario obtenerusuario(String username);

    List<Usuario> obtenerlistadeUsuarios();
}
