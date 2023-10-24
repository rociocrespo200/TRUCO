package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioLogin;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome() {
        return new ModelAndView("home");
    }

    @RequestMapping("/index")
    public ModelAndView irALogin() {

        ModelAndView modelo = new ModelAndView();
        modelo.addObject("datosLogin", new DatosLogin());
       /* modelo.addObject("pruebaHot", "Cambiando el mensaje a ver si realmente es necesario rebuildear el proyecto");*/
        modelo.setViewName("index");
        return modelo;
    }
    @RequestMapping(path = "/singin", method = RequestMethod.GET)
    public ModelAndView nuevoUsuario() {
        ModelAndView view = new ModelAndView();
        view.addObject("usuario", new Usuario());
        view.setViewName("singin");
        return view;
    }
    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String usuarioIngresado = datosLogin.getUser();
        Usuario usuarioBuscado;

        if(usuarioIngresado.contains("@")){
            usuarioBuscado = servicioLogin.consultarUsuarioPorMail(datosLogin.getUser(), datosLogin.getPassword());
        } else {
            usuarioBuscado = servicioLogin.consultarUsuarioPorUsername(datosLogin.getUser(), datosLogin.getPassword());
        }

        if (usuarioBuscado != null) {
            request.getSession().setAttribute("ROL", usuarioBuscado.getRol());

            request.getSession().setAttribute("usuario", usuarioBuscado);
            model.addObject("usuario", request.getSession().getAttribute("usuario"));
            model.setViewName("home");
            return model;
        } else {
            model.addObject("error", "Usuario o clave incorrecta");
            model.setViewName("index");
        }

        return model;
    }

    @RequestMapping(path = "/registrarme", method = RequestMethod.POST)
    public ModelAndView registrarme(@ModelAttribute("usuario") Usuario usuario) {
        ModelAndView model = new ModelAndView();
        try {
            servicioLogin.registrar(usuario);
        } catch (UsuarioExistente e) {
            model.addObject("error", "El usuario ya existe");
            model.setViewName("singin");
            return model;
        } catch (Exception e) {
            model.addObject("error", "Error al registrar el nuevo usuario");
            model.setViewName("singin");
            return model;
        }
        model.addObject("exitoso","Registro exitoso, por favor inicie sesion");
        model.setViewName("redirect:/index");
        return model;
    }

    @RequestMapping(path = "/home2", method = RequestMethod.GET)
    public ModelAndView irAHome2( HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("usuario", request.getSession().getAttribute("usuario"));
        model.setViewName("home");
        return model;
    }
}

