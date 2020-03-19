/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author rendo
 */
import java.util.Iterator;
import java.util.List;
import modelo.ProductoVO;
import java.util.ArrayList;
import modelo.CompraVO;

/**
 *
 * @author rendo
 */
public class Mensajes {
    Teclado teclado;
    public Mensajes(){
        teclado =  new Teclado();
    }
    public int opcionEntero(){
        int opcion = 0;
        System.out.println("Ingresa un dato de tipo entero");
        opcion = teclado.leerEntero();
        return opcion;
    }
    public void menu(){
        System.out.println("Elige una opcion:");
        System.out.println("1.-Agregar");
        System.out.println("2.-Mostrar");
        System.out.println("3.-Eliminar");
        System.out.println("4.-Actualizar");
        System.out.println("5.-Comprar");
        System.out.println("6.-Mostrar Compras");
        System.out.println("7-Agregar O Eliminar Cantidad de un producto");
        
        System.out.println("0.-Salir");
    }
    
    public void agregarMensaje(){
        System.out.println("Ingresando un producto a inventario");
    }
    public void eliminarMensaje(){
        System.out.println("Eliminando un producto:");
    }
    public void actualizarMensaje(){
        System.out.println("Actualizando datos de un producto");
    }
    public void mensajeCompra(){
        System.out.println("MENU DE COMPRAS");
    }
    public String leerNombre(){
        String nombre = "";
        System.out.println("Ingrese un nombre: ");
        nombre = teclado.leerCadena();
        return nombre;
    }
    public String leerClave(){
        String clave = "";
        System.out.println("Ingresa la ua clave");
        clave = teclado.leerCadena();
        return clave;
    }
    public String leerDescripcion(){
        String des ="";
        System.out.println("Ingresa la descripcion del producto");
        des = teclado.leerCadena();
        return des;
    }
    public int leerCantidad(){
        int cant = 0;
        System.out.println("Ingresa la cantida en almacen: ");
        cant = teclado.leerEntero();
        return cant;
    }
    public float leerPrecio(){
        float precio = 0;
        System.out.println("Ingresa el precio del producto");
        precio = teclado.leerEntero();
        return precio;
    }
    public String leerTipoUnidad(){
        String tipo = "";
        System.out.println("Ingresa el tipo de unidad del producto");
        tipo = teclado.leerCadena();
        return tipo;
    }
    
    public String leerTipoAtributo(){
        String tipo = "";
        System.out.println("Ingresa el tipo de atributo: ");
        tipo = teclado.leerCadena();
        return tipo;
    }
    public String leerNuevoAtributo(){
        String nuevoA="";
        System.out.println("Ingrese el nuevo atributo");
        nuevoA = teclado.leerCadena();
        return nuevoA;
    }
    public void mostrarProductos(List<ProductoVO> lista){
        System.out.println("\t Los productos son:");
        Iterator<ProductoVO>iteradorE = lista.iterator();
        while(iteradorE.hasNext()){
            System.out.println("\t \t " + iteradorE.next().toString());
        }
    }
    public void mostrarCompras(List<CompraVO> lista){
        System.out.println("\t Las compras son: ");
        Iterator<CompraVO>iteradorE = lista.iterator();
        while(iteradorE.hasNext()){
            System.out.println("\t \t " + iteradorE.next().toString());
        }
    }
    public void resultadoOperacion(boolean resultado, String operacion){
        if(resultado){
            System.out.println("\n    Resultado Exitoso al " + operacion + "\n");
        }else{
            System.out.println("\n    Resultado Fallido al " + operacion + "\n");
        }
    }
    public void menuComprar(){
        System.out.println("1.-Agregar");
        System.out.println("2.-Comprar Y Salir");
        System.out.println("0.-Salir sin comprar");
    }
    public int leerCantidadComprar(){
        System.out.println("Ingrese la cantidad a comprar: ");
        int compra = teclado.leerEntero();
        return compra;
    }
    public void insuficienteAlmacen(){
        System.out.println("No hay suficiente prodcuto en almacen");
    }
    public void productoNoEncontrado(){
        System.out.println("La clave del producto no coincide");
    }
    public void realizandoCompra(){
        System.out.println("REALIZANDO COMPRA........");
    }
    public void compraRealizada(){
        System.out.println("COMPRA REALIZADA CON EXITO!!!!");
    }
    public void saliendoMenu(){
        System.out.println("SALIENDO....");
    }
    public void opcionNoValida(){
        System.out.println("Opcion no valida");
    }
    public void eliminarAgregarCantidad(){
        System.out.println("1.-Eliminar Cantidad a Producto");
        System.out.println("2.-Agregar Cantidad a Producto");
        System.out.println("0.-SALIR");
    }
    public void mensajeEliminarCantidad(){
        System.out.println("ELIMINAR CANTIDAD: ");
    }
    public void mensajeAgregarCantidad(){
        System.out.println("AGREGAR CANTIDAD: ");
    }
    public void mensajeTicket(String tiket){
        if(tiket.equals("")){
            System.out.println("NO HA SIDO COMPRADO NADA");
        }else{
            System.out.println(tiket);
        }
    }
    public float mensajePrecioAgregar(){
        float precio = 0;
        System.out.println("Escribe el precio del producto a agregar");
        precio = teclado.leerFloat();
        return precio;
    }
   
}

