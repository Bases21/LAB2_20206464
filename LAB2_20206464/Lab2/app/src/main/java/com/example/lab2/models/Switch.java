package com.example.lab2.models;

public class Switch {
    private String marca;
    private String modelo;
    private int cantidadPuertos;
    private String tipo;
    private String estado;

    public Switch(String marca, String modelo, int cantidadPuertos, String tipo, String estado) {
        this.marca = marca;
        this.modelo = modelo;
        this.cantidadPuertos = cantidadPuertos;
        this.tipo = tipo;
        this.estado = estado;
    }

    // Getters y setters
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getCantidadPuertos() { return cantidadPuertos; }
    public void setCantidadPuertos(int cantidadPuertos) { this.cantidadPuertos = cantidadPuertos; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}