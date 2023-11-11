package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioRanking;
import com.tallerwebi.dominio.Sala;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("repositorioRanking")
public class RepositorioRankingImpl implements RepositorioRanking {


    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioRankingImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }




    @Override
    public Usuario obtenerusuario(String username) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Sala.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    @Override
    public List<Usuario> obtenerlistasdeusuarios() {
        return (List<Usuario>) sessionFactory.getCurrentSession().createCriteria(Usuario.class).list();
    }
}
