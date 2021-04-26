package com.dx.dxintercambio;

public class Post00 {

    private String user ;

    private String password;

    private  String nombre;

    private  byte[] img;

    private String folio;

    public Post00(String user, String password, String nombre, byte[] img, String folio) {
        this.user = user;
        this.password = password;
        this.nombre = nombre;
        this.img = img;
        this.folio = folio;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public byte[] getImg() {
        return img;
    }

    public String getFolio() {
        return folio;
    }
}
