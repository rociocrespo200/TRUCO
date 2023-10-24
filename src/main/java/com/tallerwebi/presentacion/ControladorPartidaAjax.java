package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugada;
import com.tallerwebi.dominio.ServicioPartida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorPartidaAjax {

    private ServicioPartida servicioPartida;

    @Autowired
    public ControladorPartidaAjax(ServicioPartida servicioPartida) {
        this.servicioPartida = servicioPartida;
    }


}
