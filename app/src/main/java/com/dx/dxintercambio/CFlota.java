package com.dx.dxintercambio;

import com.google.gson.annotations.SerializedName;

public class CFlota {

    private String id ;

    private String nombre ;

    public CFlota(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @SerializedName("id")
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
