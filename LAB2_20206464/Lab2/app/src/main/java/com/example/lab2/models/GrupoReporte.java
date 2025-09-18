package com.example.lab2.models;

import java.util.List;

public class GrupoReporte {
    private String estado;
    private int cantidad;
    private List<EquipoReporte> equipos;

    public GrupoReporte(String estado, int cantidad, List<EquipoReporte> equipos) {
        this.estado = estado;
        this.cantidad = cantidad;
        this.equipos = equipos;
    }

    // Getters
    public String getEstado() { return estado; }
    public int getCantidad() { return cantidad; }
    public List<EquipoReporte> getEquipos() { return equipos; }
}