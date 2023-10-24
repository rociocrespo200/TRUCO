package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Sala;
import com.tallerwebi.dominio.ServicioSala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

@Controller
public class ControladorSala {
    private ServicioSala servicioSala;
    @Autowired
    public ControladorSala(ServicioSala servicioSala) {
        this.servicioSala = servicioSala;

    }
    @RequestMapping(path = "/salas", method = RequestMethod.GET)
    public ModelAndView irASalas() {

        ModelAndView model = new ModelAndView();
        if (!servicioSala.obtenerlistadeSalas().isEmpty()){
            List<Sala> salas = servicioSala.obtenerlistadeSalas();
            model.addObject("ListadeSalas",salas);
            return model;

        }else {
            model.addObject("ninguna_sala", "No hay ninguna sala");
            model.setViewName("salas");
            return model;
        }


    }




    //Ver HttpServletRequest request como parametro.
    @RequestMapping(path = "/ingresar_a_sala", method = RequestMethod.POST)
    public ModelAndView IngresaraSala(@ModelAttribute("nombre_sala") String nombre_sala) {
        ModelAndView model = new ModelAndView();

        //servicioSala.obtenersala(nombre_sala).setCantidad_de_jugadores_en_sala(2);
        model.setViewName("redirect:/sala_espera");
        return model;
    }



    //codigo del controlador de iniciar partida

    @PostMapping("/crear_sala")
    public ModelAndView creadordesala( ModelAndView model) {
        int cantidadJugadoresInt = 2;
        String nombre_sala = StringAleatorio();
        Sala sala = new Sala(nombre_sala,cantidadJugadoresInt,1);
        Boolean salacreada = servicioSala.crearsala(sala);
        if (salacreada) {
            model.addObject("cantidadJugadoresInt", cantidadJugadoresInt);
            model.setViewName("sala_espera");
            return model;
        }else {
            model.addObject("error_crear_sala", "Error al crear la sala");
            model.setViewName("salas");
            return model;
        }
    }
    private String StringAleatorio() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
