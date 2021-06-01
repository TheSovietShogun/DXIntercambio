package com.dx.dxintercambio;

public class CRemolque {

    private String id ;

    private String remolques;

    private String idLinea;

    public CRemolque(String id, String remolques, String idLinea) {
        this.id = id;
        this.remolques = remolques;
        this.idLinea = idLinea;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemolques() {
        return remolques;
    }

    public void setRemolques(String remolques) {
        this.remolques = remolques;
    }

    public String getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(String idLinea) {
        this.idLinea = idLinea;
    }

    @Override
    public String toString() {
        return remolques;
    }
}
