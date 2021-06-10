package com.dx.dxintercambio;

public class CPopulateList {

    private String folioInterno ;
    private String fecha ;
    private String remolque ;
    private String usuario ;
    private String tracto ;

    public CPopulateList(String folioInterno, String fecha, String remolque, String usuario, String tracto) {
        this.folioInterno = folioInterno;
        this.fecha = fecha;
        this.remolque = remolque;
        this.usuario = usuario;
        this.tracto = tracto;
    }

    @Override
    public String toString() {
        return "CPopulateList{" +
                "folioInterno='" + folioInterno + '\'' +
                ", fecha='" + fecha + '\'' +
                ", remolque='" + remolque + '\'' +
                ", usuario='" + usuario + '\'' +
                ", tracto='" + tracto + '\'' +
                '}';
    }

    public String getFolioInterno() {
        return folioInterno;
    }

    public void setFolioInterno(String folioInterno) {
        this.folioInterno = folioInterno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRemolque() {
        return remolque;
    }

    public void setRemolque(String remolque) {
        this.remolque = remolque;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTracto() {
        return tracto;
    }

    public void setTracto(String tracto) {
        this.tracto = tracto;
    }
}
