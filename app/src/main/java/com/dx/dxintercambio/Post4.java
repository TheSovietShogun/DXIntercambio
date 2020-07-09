package com.dx.dxintercambio;

public class Post4 {

    private String user;
    private String password;
    private String fechaHora;
    private int tipoOperacion;
    private int idUsuario;
    private int idTransportista;
    private int idOperador;
    private int idUnidad;
    private int idRemolque;
    private int idLinea;
    private int estatus;
    private String comentario;

    public Post4(String user, String password, String fechaHora, int tipoOperacion, int idUsuario, int idTransportista, int idOperador, int idUnidad, int idRemolque, int idLinea, int estatus, String comentario) {
        this.user = user;
        this.password = password;
        this.fechaHora = fechaHora;
        this.tipoOperacion = tipoOperacion;
        this.idUsuario = idUsuario;
        this.idTransportista = idTransportista;
        this.idOperador = idOperador;
        this.idUnidad = idUnidad;
        this.idRemolque = idRemolque;
        this.idLinea = idLinea;
        this.estatus = estatus;
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public int getTipoOperacion() {
        return tipoOperacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdTransportista() {
        return idTransportista;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public int getIdRemolque() {
        return idRemolque;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public int getEstatus() {
        return estatus;
    }
}
