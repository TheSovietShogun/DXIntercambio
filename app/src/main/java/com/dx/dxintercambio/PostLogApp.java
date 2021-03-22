package com.dx.dxintercambio;

public class PostLogApp {

    private String user ;

    private String password ;

    private String mensaje ;

    private String app ;

    private String pantalla ;

    public PostLogApp(String user, String password, String mensaje, String app, String pantalla) {
        this.user = user;
        this.password = password;
        this.mensaje = mensaje;
        this.app = app;
        this.pantalla = pantalla;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getApp() {
        return app;
    }

    public String getPantalla() {
        return pantalla;
    }
}

