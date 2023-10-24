package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugada;
import com.tallerwebi.dominio.ServicioPartida;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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

        if(registroUsuarios.obtenerCantidadDeUsuarios() == 2){
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

        if(registroUsuarios.obtenerCantidadDeUsuarios() == 2){
            servicioPartida.crearPartida(usuariosConectados);
            model.addObject("iniciarPartida", true);
        }

        model.addObject("usuarioJava", request.getSession().getAttribute("usuario"));
        model.addObject("usuariosEnLaSala", registroUsuarios.obtenerCantidadDeUsuarios());
        model.setViewName("sala_espera");
        return model;
    }

    @RequestMapping("/partida")
    public ModelAndView iniciarPartida( HttpServletRequest request){
        ModelAndView model = new ModelAndView();

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        ArrayList<Long> usuariosConectados = new ArrayList<>(registroUsuarios.obtenerUsuariosConectados().values());
        ArrayList<Usuario> jugadoresEnLaPartida = new ArrayList<>(servicioPartida.obtenerJugadoresEnLaPartida());


        if (registroUsuarios.obtenerCantidadDeUsuarios() == 2){

            model.addObject("carta1", servicioPartida.obtenerManoDelJugador(usuario.getId()).get(0));
            model.addObject("carta2", servicioPartida.obtenerManoDelJugador(usuario.getId()).get(1));
            model.addObject("carta3", servicioPartida.obtenerManoDelJugador(usuario.getId()).get(2));
            model.addObject("usuarioJava", usuario);

            model.addObject("equipo1", jugadoresEnLaPartida.get(0).getUsername());
            model.addObject("equipo2", jugadoresEnLaPartida.get(1).getUsername());
            model.setViewName("partida");
            return model;
        }

        model.setViewName("sala_espera");
        return model;
    }
    @RequestMapping(value = "/partida", consumes = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Jugada manejarJugada(@RequestBody DatosJugada jugada) {

        servicioPartida.jugarCarta(jugada.getJugador(), jugada.getCarta());

        return servicioPartida.obtenerUltimaJugada();
    }




}