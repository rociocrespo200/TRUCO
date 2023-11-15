package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("servicioRanking")
@Transactional
public class ServicioRankingImpl implements ServicioRanking{



    private RepositorioRanking repositorioRanking;


    @Autowired
    public ServicioRankingImpl(RepositorioRanking repositorioRanking){
        this.repositorioRanking = repositorioRanking;
    }

    @Override
    public Usuario obtenerusuario(String username) {
        return repositorioRanking.obtenerusuario(username);
    }

    @Override
    public List<Usuario> obtenerlistadeUsuarios() {
        return repositorioRanking.obtenerlistasdeusuarios();
    }
}
