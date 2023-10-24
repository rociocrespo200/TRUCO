package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Usuario buscarUsuarioPorMail(String email, String password) {

        final Session session = sessionFactory.getCurrentSession();
        //EN EL MUNDO RELACIONAL ESTO SE CONVIERTE EN UN SELECT
        //CREATECRITERIA ES LA TABLA DEL SELECT
        //EQ-> DONDE "PASSWORD" ES IGUAL A PASSWORD
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public Usuario buscarUsuarioPorUsername(String username, String password) {
        final Session session = sessionFactory.getCurrentSession();
        //EN EL MUNDO RELACIONAL ESTO SE CONVIERTE EN UN SELECT
        //CREATECRITERIA ES LA TABLA DEL SELECT
        //EQ-> DONDE "PASSWORD" ES IGUAL A PASSWORD
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("username", username))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public void guardar(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }

    @Override
    public Usuario buscarMail(String email) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public Usuario buscarUsername(String username) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    @Override
    public void modificar(Usuario usuario) {
        sessionFactory.getCurrentSession().update(usuario);
    }

}
