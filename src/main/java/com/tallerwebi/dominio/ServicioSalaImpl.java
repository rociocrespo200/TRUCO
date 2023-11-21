package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioSala")
@Transactional
public class ServicioSalaImpl implements ServicioSala {
    private RepositorioSala serviciosalaDao;


    @Autowired
    public ServicioSalaImpl(RepositorioSala serviciosalaDao){
        this.serviciosalaDao = serviciosalaDao;
    }


    @Override
    public boolean crearsala(Sala sala) {
        if(!Salaexistente(sala)){
            serviciosalaDao.guardarSala(sala);
            return true;
        }
        return false;
    }

    public boolean Salaexistente(Sala sala) {
        return serviciosalaDao.Salaexistente(sala.getNombre_sala());
    }

    @Override
    public Sala obtenersala(String nombre_sala) {
        return (Sala) serviciosalaDao.buscarsala(nombre_sala);
    }

    @Override
    public List<Sala> obtenerlistadeSalas() {
        return (List<Sala>) serviciosalaDao.obtenersalas();
    }

    @Override
    public void modificarsala(Sala sala){
        serviciosalaDao.modificarSala(sala);
    }

    @Override
    public void eliminarsala(Sala sala) {
        serviciosalaDao.eliminarsala(sala);
    }


}
