package com.tallerwebi.dominio;

public class MensajeRecibido {

    private String message;
    private Long usuarioId;

    private Integer idCarta;

    public MensajeRecibido() {
    }

    public MensajeRecibido(String message, Long usuarioId, Integer idCarta) {
        this.message = message;
        this.usuarioId = usuarioId;
        this.idCarta = idCarta;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Integer getIdCarta() {
        return idCarta;
    }
}
