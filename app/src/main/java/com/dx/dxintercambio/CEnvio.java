package com.dx.dxintercambio;

public class CEnvio {

    private String mensaje ;
    private int return_value;

    public String getMensaje() {
        return mensaje;
    }

    public CEnvio(String mensaje , int return_value) {
        this.mensaje = mensaje;
        this.return_value = return_value;
    }

    public int getReturn_value() {
        return return_value;
    }
}
