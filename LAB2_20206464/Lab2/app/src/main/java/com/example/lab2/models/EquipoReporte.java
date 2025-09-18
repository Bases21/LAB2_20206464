package com.example.lab2.models;

public class EquipoReporte {
    private String tipo;
    private String marca;
    private String modelo;
    private String estado;

    public EquipoReporte(String tipo, String marca, String modelo, String estado) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.estado = estado;
    }

    // Getters
    public String getTipo() { return tipo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getEstado() { return estado; }
}