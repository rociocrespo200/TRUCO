package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioPerfl")
@Transactional
public interface ServicioPerfil {




    Usuario consultarUsuarioPorUserName(String userName , String password);

    void cambiarDatosUsuario(String userName, String password, Usuario usuarioActualizado) ;

}
