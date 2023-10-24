package com.tallerwebi.dominio;

public interface RepositorioUsuario {

    Usuario buscarUsuarioPorMail(String email, String password);
    Usuario buscarUsuarioPorUsername(String username, String password);
    void guardar(Usuario usuario);
    Usuario buscarMail(String email);
    Usuario buscarUsername(String username);
    void modificar(Usuario usuario);
}

