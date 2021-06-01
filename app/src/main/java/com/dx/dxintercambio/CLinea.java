package com.dx.dxintercambio;

public class CLinea {
    public CLinea(String id, String nombreLinea) {
        this.id = id;
        this.nombreLinea = nombreLinea;
    }

    private String id ;

    private String nombreLinea;

    public String getId() {
        return id;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    @Override
    public String toString() {
        return nombreLinea;
    }
}
