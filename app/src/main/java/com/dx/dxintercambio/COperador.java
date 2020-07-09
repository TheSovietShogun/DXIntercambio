package com.dx.dxintercambio;

import com.google.gson.annotations.SerializedName;

public class COperador {

    private String id ;

    private String nombreCompleto;

    public COperador(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    @SerializedName("id")
    public String getIdOperador() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    @Override
    public String toString() {
        return nombreCompleto;
    }
}
