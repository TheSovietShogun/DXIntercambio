package com.dx.dxintercambio;

public class Post4 {

    private String user;
    private String password;
    private String fechaHora;
    private int tipoOperacion;
    private int idUsuario;
    private int idSQL;
    private int idTransportista;
    private int idOperador;
    private int idUnidad;
    private int idRemolque;
    private int idLinea;
    private int estatusRemolque;
    private String comentario;
    private String folio ;
    private String comentarioCancel;
    private int idIntercambio;
    private int tipoMovimiento;
    private String patio ;
    private String img;
    private String imgNombre;
    private String otroUnidad;
    private String otroRemolque;

    public Post4(String user, String password, String fechaHora, int tipoOperacion, int idUsuario, int idSQL, int idTransportista, int idOperador, int idUnidad, int idRemolque, int idLinea, int estatusRemolque, String comentario, String folio, String comentarioCancel, int idIntercambio, int tipoMovimiento, String patio, String img, String imgNombre, String otroUnidad, String otroRemolque) {
        this.user = user;
        this.password = password;
        this.fechaHora = fechaHora;
        this.tipoOperacion = tipoOperacion;
        this.idUsuario = idUsuario;
        this.idSQL = idSQL;
        this.idTransportista = idTransportista;
        this.idOperador = idOperador;
        this.idUnidad = idUnidad;
        this.idRemolque = idRemolque;
        this.idLinea = idLinea;
        this.estatusRemolque = estatusRemolque;
        this.comentario = comentario;
        this.folio = folio;
        this.comentarioCancel = comentarioCancel;
        this.idIntercambio = idIntercambio;
        this.tipoMovimiento = tipoMovimiento;
        this.patio = patio;
        this.img = img;
        this.imgNombre = imgNombre;
        this.otroUnidad = otroUnidad;
        this.otroRemolque = otroRemolque;
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

    public int getIdSQL() {
        return idSQL;
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

    public int getEstatusRemolque() {
        return estatusRemolque;
    }

    public String getComentario() {
        return comentario;
    }

    public String getFolio() {
        return folio;
    }

    public String getComentarioCancel() {
        return comentarioCancel;
    }

    public int getIdIntercambio() {
        return idIntercambio;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getPatio() {
        return patio;
    }

    public String getImg() {
        return img;
    }

    public String getImgNombre() {
        return imgNombre;
    }

    public String getOtroUnidad() {
        return otroUnidad;
    }

    public String getOtroRemolque() {
        return otroRemolque;
    }
}
