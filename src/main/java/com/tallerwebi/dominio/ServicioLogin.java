package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.UsuarioExistente;

public interface ServicioLogin {

    Usuario consultarUsuarioPorMail(String email, String password);
    Usuario consultarUsuarioPorUsername(String username, String password);
    void registrar(Usuario usuario) throws UsuarioExistente;

}
