package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioRanking {
    Usuario obtenerusuario(String username);

    List<Usuario> obtenerlistasdeusuarios();
}
