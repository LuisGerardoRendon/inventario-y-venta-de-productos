/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author rendo
 */
public class ProductoVO {
    private String clave, nombre, descripcion, tipoUnidad;
    private int cantidad;
    private float precio;

    public ProductoVO(String clave, String nombre, String descripcion, String tipoUnidad, int cantidad, float precio) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoUnidad = tipoUnidad;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ProductoVO{" + "clave=" + clave + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipoUnidad=" + tipoUnidad + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
    
    
}
