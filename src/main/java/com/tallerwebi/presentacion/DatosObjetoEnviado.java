package com.tallerwebi.presentacion;

public class DatosObjetoEnviado {

    private String tipo;

    private Object obj;

    public DatosObjetoEnviado(){

    }
    public DatosObjetoEnviado(String tipo, Object obj) {
        this.tipo = tipo;
        this.obj = obj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
