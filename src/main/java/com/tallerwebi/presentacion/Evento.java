package com.tallerwebi.presentacion;

public class Evento {

    String nombredeEvento;

    Integer valordeEvento;

    public Evento(String nombredeEvento,Integer valordeEvento){
        this.nombredeEvento=nombredeEvento;
        this.valordeEvento=valordeEvento;

    }


    public String getNombredeEvento() {
        return nombredeEvento;
    }

    public void setNombredeEvento(String nombredeEvento) {
        this.nombredeEvento = nombredeEvento;
    }

    public Integer getValordeEvento() {
        return valordeEvento;
    }

    public void setValordeEvento(Integer valordeEvento) {
        this.valordeEvento = valordeEvento;
    }
}
