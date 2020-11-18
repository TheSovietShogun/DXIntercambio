package com.dx.dxintercambio;

public class CLlanta {

    private String nombre ;

    public CLlanta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
