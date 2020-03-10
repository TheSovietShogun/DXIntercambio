package com.example.dxintercambio;

public class CRemolque {

    private int id ;

    private String remolques;

    public CRemolque(int id, String clave) {
        this.id = id;
        this.remolques = clave;
    }

    public int getId() {
        return id;
    }

    public String getRemolques() {
        return remolques;
    }

    @Override
    public String toString() {
        return remolques;
    }
}
