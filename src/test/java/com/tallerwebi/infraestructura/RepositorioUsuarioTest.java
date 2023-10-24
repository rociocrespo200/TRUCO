package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioUsuarioTest {
    @Autowired
    private RepositorioUsuario repositorioImplementacion;
/*
    @Test
    @Transactional
    @Rollback
    public void buscarUsuarioPorMailTest(){
        // Preparacion
        String username = "slowik";
        String correoBuscable = "aletogu00@gmail.com";
        String contrasenia = "probando";

        // Ejecucion


        //Comprobacion
        Usuario usuarioEncontrado = repositorioImplementacion.buscarUsuarioPorUsername(username, contrasenia);

        assertThat(usuarioEncontrado, notNullValue());
    }*/
}
