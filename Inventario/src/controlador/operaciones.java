/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.CompraVO;
import modelo.ProductoVO;
import modelo.Producto_DAO_Imp;
import modelo.Compra_DA0_Imp;
import vista.Mensajes;

/**
 *
 * @author rendo
 */
public class operaciones {
    Mensajes ms ;
    Producto_DAO_Imp producto_DAO_Imp;
    Compra_DA0_Imp compra_dao_imp;
    public operaciones(){
        this.ms= new Mensajes();
        this.producto_DAO_Imp = new Producto_DAO_Imp();
        this.compra_dao_imp = new Compra_DA0_Imp();
    }
    public void guardar(String clave, String nombre, String descripcion, String tipoUnidad, int cantidad, int precio){
        ProductoVO producto = new ProductoVO(clave, nombre,  descripcion, tipoUnidad, cantidad,precio);
        this.producto_DAO_Imp.create(producto);
    }
    public void mostrar(){
        List<ProductoVO> lista = this.producto_DAO_Imp.readAll();
        ms.mostrarProductos(lista);
    }
    public void ejecutarAplicacion(){
        int opcion = 0;
        do{
            ms.menu();
            opcion = ms.opcionEntero();
            switch(opcion){
                case 1:
                    ms.agregarMensaje();
                    String clave = "NULL";
                    String nombre = ms.leerNombre();
                    String descripcion = ms.leerDescripcion();
                    int cantidad = ms.leerCantidad();
                    float precio = ms.leerPrecio();
                    String tipoUnidad = ms.leerTipoUnidad();
                    ProductoVO producto = new ProductoVO(clave,  nombre,  descripcion,  tipoUnidad,  cantidad, precio);
                    boolean creado  = this.producto_DAO_Imp.create(producto);
                    ms.resultadoOperacion(creado, "crear");
                    break;
                case 2:
                    List<ProductoVO> lista = this.producto_DAO_Imp.readAll();
                    ms.mostrarProductos(lista);
                    break;
                case 3:
                {
                    ms.eliminarMensaje();
                    String claveEliminar = ms.leerClave();
                    boolean elminado = this.producto_DAO_Imp.eliminar(claveEliminar);
                    ms.resultadoOperacion(elminado, "eliminar");
                }
                    break; 
                case 4:
                {
                    ms.actualizarMensaje();
                    String claveActualizar = ms.leerClave();
                    String tipoAtributo = ms.leerTipoAtributo();
                    String nuevoAtributo = ms.leerNuevoAtributo();
                    boolean actualizado = this.producto_DAO_Imp.actualizar(claveActualizar, tipoAtributo, nuevoAtributo);
                    ms.resultadoOperacion(actualizado, "actualizar");
                }
                    break;
                case 5:
                {
                    ms.mensajeCompra();
                    String compras = this.producto_DAO_Imp.leerCompras();
                    ms.mensajeTicket(compras);
                    
                }break;
                case 6:
                {
                    List<CompraVO> listaCompras = this.compra_dao_imp.readAll();
                    ms.mostrarCompras(listaCompras);
                }
                    break;
                case 7:
                {
                    int opcionEliminarAgregarCantidad=0;
                    do{
                        ms.eliminarAgregarCantidad();
                        opcionEliminarAgregarCantidad = ms.opcionEntero();
                        switch(opcionEliminarAgregarCantidad){
                            case 1:
                            {
                                ms.mensajeEliminarCantidad();
                                String claveEliminarCantidad = ms.leerClave();
                                int cantidadEliminar = ms.leerCantidad();
                                this.producto_DAO_Imp.eliminarCantidad(claveEliminarCantidad, cantidadEliminar);
                            }break;
                            case 2:
                            {
                                ms.mensajeAgregarCantidad();
                                String claveAgregar = ms.leerClave();
                                int cantidadAgregar = ms.leerCantidad();
                                float precioNuevo = ms.mensajePrecioAgregar();
                                this.producto_DAO_Imp.agregarCantidad(claveAgregar, cantidadAgregar, precioNuevo);
                                
                            }break;
                            case 0:
                            {
                                ms.saliendoMenu();
                            }break;
                            default:
                            {
                                ms.opcionNoValida();
                            }break;
                        }
                    }while(opcionEliminarAgregarCantidad!=0);
                }
                    
                    
                    
                   
                    
                    
                case 0:
                    System.out.println("Saliendo");
                    break;
                    
                default:
                    System.out.println("Opcion no valida...");
                    
            }
            
        }while(opcion!=0);
    }
    
}
