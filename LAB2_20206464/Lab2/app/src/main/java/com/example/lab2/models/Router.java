package com.example.lab2.models;

public class Router {
    private String marca;
    private String modelo;
    private String velocidadSoportada;
    private String estado;

    public Router(String marca, String modelo, String velocidadSoportada, String estado) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadSoportada = velocidadSoportada;
        this.estado = estado;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getVelocidadSoportada() { return velocidadSoportada; }
    public void setVelocidadSoportada(String velocidadSoportada) { this.velocidadSoportada = velocidadSoportada; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}