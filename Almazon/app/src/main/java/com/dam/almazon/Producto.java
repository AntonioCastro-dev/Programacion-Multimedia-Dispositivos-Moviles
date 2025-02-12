package com.dam.almazon;

public class Producto {

    //Atributos
    private String nombre;
    private int unidades;
    private String descripcion;

    //Constructor
    public Producto(String nombre, int unidades, String descripcion) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getUnidades() {
        return unidades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}