/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.List;

/**
 *
 * @author rendo
 */
public interface Producto_DAO {
    public boolean create(ProductoVO producto);
    public List<ProductoVO> readAll();
    public boolean eliminar(String clave);
    public boolean actualizar(String clave, String tipoAtributo, String nuevoAtributo);
    public boolean comprar(String clave, int cantidad);
    public boolean disponible(String clave, int cantidad);
    public String leerCompras();
    public ProductoVO existe(String clave);
    public void registarCompra(CompraVO c, int clave);
    public boolean eliminarCantidad(String clave, int cantidad);
    public boolean agregarCantidad(String clave, int cantidad, float precio);
}
