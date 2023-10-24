package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioSala;
import com.tallerwebi.dominio.Sala;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioSala")
public class RepositorioSalaImpl implements RepositorioSala {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioSalaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public boolean guardarSala(Sala sala) {
        if(!Salaexistente(sala.getNombre_sala())){
            sessionFactory.getCurrentSession().save(sala);
            return true;
        }
        return false;
    }
    @Override
    public boolean Salaexistente(String nombre_sala) {
        if (buscarsala(nombre_sala)!=null){
            return true;
        }
        return false;
    }

    @Override
    public Sala buscarsala(String nombre) {
        return (Sala) sessionFactory.getCurrentSession().createCriteria(Sala.class)
                .add(Restrictions.eq("nombre_sala", nombre))
                .uniqueResult();
    }
    @Override
    public List<Sala> obtenersalas() {
        return (List<Sala>) sessionFactory.getCurrentSession().createCriteria(Sala.class).list();
    }

}



