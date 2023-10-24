package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class WebSocketRegistroDeUsuarios {
    private final Map<String, Long> userSessionMap = new ConcurrentHashMap<>();

    public void registerUser(String sessionId, Long usuarioId) {
        userSessionMap.put(sessionId, usuarioId);
    }

    public void removeUser(String sessionId) {
        userSessionMap.remove(sessionId);
    }

    public Long getUserBySessionId(String sessionId) {
        return userSessionMap.get(sessionId);
    }

    public Map<String, Long> obtenerUsuariosConectados() {
        return userSessionMap;
    }

    public Integer obtenerCantidadDeUsuarios() {return userSessionMap.size();}



}
