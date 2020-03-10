package com.example.dxintercambio;

public class Post2 {

    private String user ;

    private String password;

    private  String idFlota;

    public Post2(String user, String password, String idFlota) {
        this.user = user;
        this.password = password;
        this.idFlota = idFlota;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getIdFlota() {
        return idFlota;
    }


}
