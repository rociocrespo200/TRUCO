package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class ServicioPerfilImpl implements ServicioPerfil {

    private RepositorioUsuario servicioPerfilDao;

    @Autowired
    public ServicioPerfilImpl (RepositorioUsuario servicioPerfilDao){
        this.servicioPerfilDao = servicioPerfilDao;
    }


    @Override
    public Usuario consultarUsuarioPorUserName(String userName , String password) {
        return servicioPerfilDao.buscarUsuarioPorUsername(userName, password);
    }

    @Override
    @Transactional
    public void cambiarDatosUsuario( String userName, String password, Usuario usuarioActualizado) {

        Usuario usuarioExistente = servicioPerfilDao.buscarUsuarioPorUsername(userName,password);
        if (usuarioExistente != null) {
            // Actualiza los datos del usuario con los nuevos datos.
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());

            // Puedes continuar actualizando otros campos según tu entidad Usuario.

            // Guarda los cambios en la base de datos.
            servicioPerfilDao.modificar(usuarioExistente);
        }

    }
}
