package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

    private RepositorioUsuario servicioLoginDao;
    private RepositorioUsuario servicioLogin2;

    @Autowired
    public ServicioLoginImpl(RepositorioUsuario servicioLoginDao){
        this.servicioLoginDao = servicioLoginDao;
    }

    @Override
    public Usuario consultarUsuarioPorMail(String email, String password) {
        return servicioLoginDao.buscarUsuarioPorMail(email, password);
    }

//    @Override
//    public Jugador buscarUsuario(String usuario, String contrasena) {
//        return servicioLoginDao.buscarUsuario(usuario, contrasena);
//    }

    @Override
    public Usuario consultarUsuarioPorUsername(String username, String password) {
        return servicioLoginDao.buscarUsuarioPorUsername(username, password);
    }

//    @Override
//    public Jugador buscarUsuario(String usuario, String contrasena) {
//        return servicioLoginDao.buscarUsuario(usuario, contrasena);
//    }

    @Override
    public void registrar(Usuario usuario) throws UsuarioExistente {
        Usuario mailEncontrado = servicioLoginDao.buscarUsuarioPorMail(usuario.getEmail(), usuario.getPassword());
        Usuario usernameEncontrado = servicioLoginDao.buscarUsuarioPorUsername(usuario.getUsername(), usuario.getPassword());
        if(mailEncontrado != null && usernameEncontrado != null){
            throw new UsuarioExistente();
        }
        servicioLoginDao.guardar(usuario);
    }

}

