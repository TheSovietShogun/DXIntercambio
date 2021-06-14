package com.dx.dxintercambio;

public class CPopulateList {

    private String folioInterno ;
    private String fechaInicio ;
    private String remolque ;
    private String usuario ;
    private String tracto ;
    private String estatus ;
    private String fechaFin ;


    @Override
    public String toString() {
        return "CPopulateList{" +
                "folioInterno='" + folioInterno + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", remolque='" + remolque + '\'' +
                ", usuario='" + usuario + '\'' +
                ", tracto='" + tracto + '\'' +
                ", estatus='" + estatus + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                '}';
    }

    public CPopulateList(String folioInterno, String fechaInicio, String remolque, String usuario, String tracto, String estatus, String fechaFin) {
        this.folioInterno = folioInterno;
        this.fechaInicio = fechaInicio;
        this.remolque = remolque;
        this.usuario = usuario;
        this.tracto = tracto;
        this.estatus = estatus;
        this.fechaFin = fechaFin;
    }

    public String getFolioInterno() {
        return folioInterno;
    }

    public void setFolioInterno(String folioInterno) {
        this.folioInterno = folioInterno;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
