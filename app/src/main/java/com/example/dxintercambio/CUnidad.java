package com.example.dxintercambio;

import com.google.gson.annotations.SerializedName;

public class CUnidad {

    private int id ;

    private String clave ;

    public CUnidad(int id, String clave) {
        this.id = id;
        this.clave = clave;
    }

    public int getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    @Override
    public String toString() {
        return clave;
    }
}
