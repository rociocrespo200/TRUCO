package com.tallerwebi.dominio;

public class MensajeEnviado {
    private String content;
    private Long idUsuario;


    public MensajeEnviado(String content, Long idUsuario) {
        this.content = content;
        this.idUsuario = idUsuario;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
