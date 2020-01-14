package com.example.dxintercambio;

public class CUsuario {

    private String idEmpresa ;
    private String idUsuario ;
    private String nombreUsuario ;
    private String idUnidad ;
    private String claveUnidad;
    private String idOperador ;
    private String nombreOperador ;
    private String idFlota ;
    private String idViaje ;


    public CUsuario(String idEmpresa, String idUsuario, String nombreUsuario, String idUnidad, String claveUnidad, String idOperador, String nombreOperador, String idFlota, String idViaje) {
        this.idEmpresa = idEmpresa;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idUnidad = idUnidad;
        this.claveUnidad = claveUnidad;
        this.idOperador = idOperador;
        this.nombreOperador = nombreOperador;
        this.idFlota = idFlota;
        this.idViaje = idViaje;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getIdUnidad() {
        return idUnidad;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public String getIdOperador() {
        return idOperador;
    }

    public String getNombreOperador() {
        return nombreOperador;
    }

    public String getIdFlota() {
        return idFlota;
    }

    public String getIdViaje() {
        return idViaje;
    }
}
