package com.example.dxintercambio;

public class CUsuario {

    private String respuesta ;

    private  String idUsuario ;

    public CUsuario(String respuesta, String idUsuario) {
        this.respuesta = respuesta;
        this.idUsuario = idUsuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
}
