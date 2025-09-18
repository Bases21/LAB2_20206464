package com.example.lab2.models;

public class AccessPoint {
    private String marca;
    private String frecuencia;
    private int alcance;
    private String estado;

    public AccessPoint(String marca, String frecuencia, int alcance, String estado) {
        this.marca = marca;
        this.frecuencia = frecuencia;
        this.alcance = alcance;
        this.estado = estado;
    }

    // Getters y setters
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public int getAlcance() { return alcance; }
    public void setAlcance(int alcance) { this.alcance = alcance; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}