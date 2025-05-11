package com.tecsup.laboratorio_07.model;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int idCategoria;
    // Getters and Setters
    public int getIdProducto() {return idProducto;}
    public void setIdProducto(int idProducto) {this.idProducto = idProducto;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public double getPrecio() {return precio;}
    public void setPrecio(double precio) {this.precio = precio;}
    public int getIdCategoria() {return idCategoria;}
    public void setIdCategoria(int idCategoria) {this.idCategoria = idCategoria;}
}
