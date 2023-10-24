 package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioPartida;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

 @Controller
public class ControladorIniciarPartida {



//     @GetMapping("/iniciarPartida")
//     public String mostrarFormulario() {
//         return "iniciarPartida";
//     }
//
//     @RequestMapping("/esperarSala")
//     public ModelAndView esperarSala(@ModelAttribute("cantidadDejugadores") String cantidadJugadores, HttpServletRequest request) {
//         ModelAndView model = new ModelAndView();
//         request.getSession().setAttribute("cantidadDeJugadores", cantidadJugadores);
//         model.addObject("cantidadJugadoresInt", cantidadJugadores);
//
//        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
//        servicioPartida.crearPartida(usuario, 1);
//
//         model.setViewName("salas");
//         return model;
//     }

}