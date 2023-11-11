package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Sala;
import com.tallerwebi.dominio.ServicioRanking;
import com.tallerwebi.dominio.ServicioSala;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

@Controller
public class ControladorRanking {

    private ServicioRanking servicioRanking;
    @Autowired
    public ControladorRanking(ServicioRanking servicioRanking) {
        this.servicioRanking = servicioRanking;

    }



    @RequestMapping(path = "/ranking", method = RequestMethod.GET)
    public ModelAndView irARanking() {

        ModelAndView model = new ModelAndView();
        if (!servicioRanking.obtenerlistadeUsuarios().isEmpty()){
            TreeSet<Usuario> usuarios = new TreeSet<>(new Comparator<Usuario>() {
                @Override
                public int compare(Usuario o1, Usuario o2) {
                    return -o1.getPuntos_ranking().compareTo(o2.getPuntos_ranking());
                }
            });

            usuarios.addAll(servicioRanking.obtenerlistadeUsuarios());


            model.addObject("ListadeUsuarios",usuarios);
            return model;

        }else {
            model.addObject("ninguna_usuario", "No hay ningun usuario");
            model.setViewName("ranking");
            return model;
        }


    }

    @RequestMapping(path = "/IngresaraUsuario", method = RequestMethod.POST)
    public ModelAndView IngresaraUsuario(@ModelAttribute("username_usuario") String username_usuario) {
        ModelAndView model = new ModelAndView();
        Usuario usuario = servicioRanking.obtenerusuario(username_usuario);
        model.addObject("usuario", usuario);
        model.setViewName("perfil");
        return model;
    }


}
