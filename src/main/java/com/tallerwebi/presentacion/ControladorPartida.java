package com.tallerwebi.presentacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugada;
import com.tallerwebi.dominio.ServicioPartida;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller

public class ControladorPartida {
    private ServicioPartida servicioPartida;
    private final WebSocketRegistroDeUsuarios registroUsuarios;

    @Autowired
    public ControladorPartida(ServicioPartida servicioPartida, WebSocketRegistroDeUsuarios registroUsuarios) {
        this.registroUsuarios = registroUsuarios;
        this.servicioPartida = servicioPartida;
    }


    @GetMapping("/iniciarPartida")
    public String mostrarFormulario() {
        return "iniciarPartida";
    }

    @RequestMapping("/esperarSala")
    public ModelAndView esperarSala(@ModelAttribute("cantidadDejugadores") String cantidadJugadores, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        request.getSession().setAttribute("cantidadDeJugadores", cantidadJugadores);
        model.addObject("cantidadJugadoresInt", cantidadJugadores);

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        // Registra al usuario con su sesión
        registroUsuarios.registerUser(usuario.getUsername(), usuario.getId());


        ArrayList<Long> usuariosConectados = new ArrayList<>(registroUsuarios.obtenerUsuariosConectados().values());

        if (registroUsuarios.obtenerCantidadDeUsuarios() == 2) {
            servicioPartida.crearPartida(usuariosConectados);

            //Hacer que se envie que ya estan todos los usuarios conectados.
            model.addObject("iniciarPartida", true);
        }

        model.addObject("usuarioJava", request.getSession().getAttribute("usuario"));
        model.addObject("usuariosEnLaSala", registroUsuarios.obtenerCantidadDeUsuarios());
        model.setViewName("sala_espera");
        return model;
    }

    @RequestMapping("/sala")
    public ModelAndView esperarSala3(@ModelAttribute("cantidadDejugadores") String cantidadJugadores, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();


        model.addObject("usuariosEnLaSala", registroUsuarios.obtenerCantidadDeUsuarios());

        model.setViewName("sala_espera");

        return model;
    }

    @RequestMapping("/sala_espera")
    public ModelAndView esperarSala2(@ModelAttribute("cantidadDejugadores") String cantidadJugadores, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        request.getSession().setAttribute("cantidadDeJugadores", cantidadJugadores);
        model.addObject("cantidadJugadoresInt", cantidadJugadores);

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        // Registra al usuario con su sesión
        registroUsuarios.registerUser(usuario.getUsername(), usuario.getId());


        ArrayList<Long> usuariosConectados = new ArrayList<>(registroUsuarios.obtenerUsuariosConectados().values());

        if (registroUsuarios.obtenerCantidadDeUsuarios() == 2) {
            servicioPartida.crearPartida(usuariosConectados);
            model.addObject("iniciarPartida", true);
        }

        model.addObject("usuarioJava", request.getSession().getAttribute("usuario"));
        model.addObject("usuariosEnLaSala", registroUsuarios.obtenerCantidadDeUsuarios());
        model.setViewName("sala_espera");
        return model;
    }

    @RequestMapping("/partida")
    public ModelAndView iniciarPartida(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        ArrayList<Long> usuariosConectados = new ArrayList<>(registroUsuarios.obtenerUsuariosConectados().values());
        ArrayList<Equipo> equipos = new ArrayList<>(servicioPartida.obtenerJugadoresEnLaPartida());


        if (registroUsuarios.obtenerCantidadDeUsuarios() == 2) {

            model.addObject("carta1", servicioPartida.obtenerManoDelJugador(usuario.getId()).get(0));
            model.addObject("carta2", servicioPartida.obtenerManoDelJugador(usuario.getId()).get(1));
            model.addObject("carta3", servicioPartida.obtenerManoDelJugador(usuario.getId()).get(2));
            model.addObject("usuarioJava", usuario);

            model.addObject("equipo1", equipos.get(0).getJugadores().get(0).getUsername());
            model.addObject("equipo1_pto", equipos.get(0).getPuntos());
            model.addObject("equipo2", equipos.get(1).getJugadores().get(0).getUsername());
            model.addObject("equipo2_pto", equipos.get(1).getPuntos());
            model.setViewName("partida");
            return model;
        }

        model.setViewName("sala_espera");
        return model;
    }

    @RequestMapping(value = "/partida", consumes = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Object manejarJugada(@RequestBody DatosObjetoEnviado objeto) {
        ObjectMapper objectMapper = new ObjectMapper();

        String tipo = objeto.getTipo();

        if (tipo.equals("jugada")) {
            DatosJugada jugada = objectMapper.convertValue(objeto.getObj(), DatosJugada.class);

            servicioPartida.jugarCarta(jugada.getJugador(), jugada.getCarta());
            if (servicioPartida.validarSiTerminoRonda()) {
                ModelAndView model = new ModelAndView();
                model.setViewName("partida");
                model.addObject("popupTerminoRonda", "La ronda ya termino");
                return model;
            }

            Jugada ultimaJugada = servicioPartida.obtenerUltimaJugada();
            if (ultimaJugada.getCarta().getId() != jugada.getCarta()) {
                return null;
            }
            return servicioPartida.obtenerUltimaJugada();

        } else if (tipo.equals("evento")) {
            DatosEvento evento = objectMapper.convertValue(objeto.getObj(), DatosEvento.class);

            if (evento.getFinalizado()) {
                //registrar evento en el programa
                servicioPartida.registrarEvento(evento);
                return servicioPartida.obtenerUltimoEvento().getNombre();
            } else {
                return evento.getNombre();
            }

        }

        return "Solicitud no válida";

    }


}


