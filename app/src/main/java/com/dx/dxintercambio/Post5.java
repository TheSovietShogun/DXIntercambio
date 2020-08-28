package com.dx.dxintercambio;

public class Post5 {

    private String user ;

    private String password;

    private  String nombre;

    private  String img;

    private String folio;


    public Post5(String user, String password, String nombre , String img , String folio ) {
        this.user = user;
        this.password = password;
        this.nombre =  nombre;
        this.img = img;
        this.folio = folio;

    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() { return nombre; }

    public String getImg() { return img; }

    public String getFolio() {return folio; }
}
