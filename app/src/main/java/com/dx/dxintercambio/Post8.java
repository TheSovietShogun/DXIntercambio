package com.dx.dxintercambio;

public class Post8 {

    private String user ;

    private String password ;

    private String idSQL;

    public Post8(String user, String password, String idSQL) {
        this.user = user;
        this.password = password;
        this.idSQL = idSQL;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdSQL() {
        return idSQL;
    }

    public void setIdSQL(String idSQL) {
        this.idSQL = idSQL;
    }
}

