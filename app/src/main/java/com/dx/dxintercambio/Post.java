package com.dx.dxintercambio;

public class Post {

    private String user ;

    private String password ;

    public Post(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
