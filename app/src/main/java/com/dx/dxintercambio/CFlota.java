package com.dx.dxintercambio;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CFlota {

    private String id ;

    private String clave ;

    @SerializedName("nombreTransportista")
    private String nombre ;

    @Override
    public String toString() {
        return nombre;
    }



    public CFlota(String id, String clave, String nombre) {

        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
