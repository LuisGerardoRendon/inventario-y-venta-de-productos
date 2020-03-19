/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import vista.Mensajes;
import vista.Teclado;
/**
 *
 * @author rendo
 */
public class Producto_DAO_Imp implements Producto_DAO{
    Mensajes ms = new Mensajes();

    @Override
    public boolean create(ProductoVO producto) {
        boolean creado = false;
        Statement stm = null;
        Connection con = null;
        String clave = producto.getClave();
        String nom = producto.getNombre();
        String desc = producto.getDescripcion();
        int cantidad = producto.getCantidad();
        float precio = producto.getPrecio();
        String unidad = producto.getTipoUnidad();
        String sql = "INSERT INTO productos VALUES("+clave;
               sql+=", '"+nom+"', '"+desc+"'";
               sql+=", "+"'"+cantidad+"', '"+precio+"', '";
               sql+=unidad+"'"+")";
        ConexionBD cc = new ConexionBD();
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            stm.execute(sql);
            creado = true;
            con.close();
            stm.close();
        }catch(SQLException e){
            System.out.println("Error, Clase Producto_DAO_Imp, metodo create");
            e.printStackTrace();
        }
        return creado;
    }

    @Override
    public List<ProductoVO> readAll() {
        Connection con = null;
        Statement stm = null;
        ConexionBD cc = new ConexionBD();
        ResultSet rs = null;
        String sql = "SELECT * FROM productos ORDER BY clave;";
        List<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
        try{
            con = cc.conectarMySQL();
            stm= con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                ProductoVO c =  new ProductoVO(rs.getString("clave"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("tipoUnidad"), rs.getInt("cantidad"), rs.getFloat("precio"));
                //c.toString();
                listaProductos.add(c);
            }
            con.close();
            stm.close();
            rs.close();
            
        }catch(SQLException e){
            System.out.println("Error, Clase Producto_DAO_Imp, metodo readAll");
            e.printStackTrace();
        }
        return listaProductos;
    }

    @Override
    public boolean eliminar(String clave) {
        boolean eliminado = false;
        Connection con = null;
        Statement stm = null;
        ConexionBD cc = new ConexionBD();
        String sql = " DELETE FROM productos WHERE clave = '" + clave+"';";
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            stm.execute(sql);
            eliminado = true;
            con.close();
            stm.close();
        }catch(SQLException e){
            System.out.println("Error en clase Producto_DAO_Imp, metodo eliminar");
            e.printStackTrace();
        }
        return eliminado;
    }
    
    @Override
    public boolean actualizar(String clave, String tipoAtributo, String nuevoAtributo) {
        Connection con = null;
        Statement stm = null;
        ConexionBD cc = new ConexionBD();
        String sql = "UPDATE productos SET " + tipoAtributo + " = '" + nuevoAtributo + "' WHERE clave = '"+clave+"';";
        boolean actualizado = false;
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            stm.execute(sql);
            actualizado = true;
        }catch(SQLException e){
            System.out.println("Error en clase Producto_DAO_Imp, metodo actualizar");
            e.printStackTrace();
        }
        return actualizado;
    }
    @Override
    public boolean comprar(String clave, int cantidad){
        boolean comprado = false;
        Connection con = null;
        Statement stm = null;
        Statement stmUpDate = null;
        ConexionBD cc = new ConexionBD();
        ResultSet rs = null;
        int nuevaCantidad = 0;
        String sql = "SELECT * FROM productos WHERE clave ='"+clave+"';";
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            int cantidadDisponible = 0;
            while(rs.next()){
                ProductoVO producto = new ProductoVO(rs.getString("clave"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("tipoUnidad"), rs.getInt("cantidad"), rs.getInt("precio"));
                 cantidadDisponible = producto.getCantidad();
                 nuevaCantidad = cantidadDisponible- cantidad;
            }
            String sql2 = "UPDATE productos SET cantidad " + " = '" + nuevaCantidad + "' WHERE clave = '"+clave+"';";
            stmUpDate = con.createStatement();
            stmUpDate.execute(sql2);
            con.close();
            stm.close();
            rs.close();
            
        }catch(SQLException e){
            System.out.println("Error en cla clase Producto_DAO_Imp, metodo comprar");
            e.printStackTrace();
        }
        return comprado;
    }
    public boolean disponible(String clave, int cantidad){
        boolean disponible = false;
        Connection con = null;
        Statement stm = null;
        ConexionBD cc = new ConexionBD();
        ResultSet rs = null;
        String sql = "SELECT * FROM productos WHERE clave ='"+clave+"';";
        
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            int cantidadDisponible = 0;
            while(rs.next()){
                ProductoVO producto = new ProductoVO(rs.getString("clave"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("tipoUnidad"), rs.getInt("cantidad"), rs.getFloat("precio"));
                 cantidadDisponible = producto.getCantidad();
            }
            if(cantidadDisponible>=cantidad){
                disponible = true;
            }
        }catch(SQLException e){
            System.out.println("Error en clase Producto_DAO_Imp, metodo disponible");
            e.printStackTrace();
        }
        return disponible;
    }
    
    public String leerCompras(){
        int opcion = 0;
        String [] claves = new String[100];
        int[] cantidades = new int[100];
        //Teclado teclado = new Teclado();
        int contador = 0;
        String ticket = "";
        String totalString = "";
        do{
            ms.menuComprar();
            opcion = ms.opcionEntero();
            switch(opcion){
                case 1:
                    String clave = ms.leerClave();
                    ProductoVO producto = this.existe(clave);
                    if(producto!=null){
                        int cantidad = ms.leerCantidadComprar();
                        if(this.disponible(clave,cantidad)){
                            ticket+="\n Clave:"+ producto.getClave() + " Nombre: "+ producto.getNombre() +" Cantidad: "+cantidad+" Precio: "+producto.getPrecio()*1.15 + "Subtotal: " + cantidad *(producto.getPrecio()*1.15);
                            claves[contador] = clave;
                            cantidades[contador] = cantidad;
                            contador++;
                        }else{
                            ms.insuficienteAlmacen();
                        }
                    }else{
                        ms.productoNoEncontrado();
                    }
                    break;
                case 2:
                    ms.realizandoCompra();
                    ProductoVO productoCalcularTotal = null;
                    double total = 0;
                    for(int i = 0; i <contador;i++){
                        this.comprar(claves[i], cantidades[i]);  
                        productoCalcularTotal = this.existe(claves[i]);
                        total += cantidades[i]*(productoCalcularTotal.getPrecio() *1.15);
                    }
                    totalString = "\nTOTAL:                 " + total;
                    ticket += totalString;
                    CompraVO compra = new CompraVO();   
                    String sqlInsrtarCompra = "INSERT INTO Compra VALUES(NULL, '"+compra.getFecha()+"', '"+compra.getHora()+"');";
                    String sqlRecuperarCompra = "SELECT *FROM compra WHERE idCompra = (SELECT MAX( idCompra )  FROM compra);";
                    Statement stm = null;
                    Connection con = null;
                    ResultSet rs = null;
                    ConexionBD cc = new ConexionBD();
                    String idCompra;
                    try{
                        con = cc.conectarMySQL();
                        stm = con.createStatement();
                        stm.execute(sqlInsrtarCompra);
                        rs = stm.executeQuery(sqlRecuperarCompra);
                        CompraVO com = null;
                        while(rs.next()){
                            com = new CompraVO(Integer.toString(rs.getInt("idCompra")), rs.getString("fecha"), rs.getString("hora"));
                        }
                        idCompra = com.getIdCOmpra();
                        for(int i = 0; i<contador;i++){
                            String sqlCompra_Producto = "INSERT INTO compra_producto VALUES ("+claves[i]+","+idCompra+","+cantidades[i]+");";
                            stm.execute(sqlCompra_Producto);
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    ms.compraRealizada();
                    break;
                case 0:
                    ms.saliendoMenu();
                    break;
                default:
                    ms.opcionNoValida();
            }
            
        }while(opcion!=0&&opcion!=2);
        return ticket;
    }
    public ProductoVO existe(String clave){
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        ConexionBD cc = new ConexionBD();
        boolean existe = false;
        String sql = "SELECT * FROM Productos WHERE clave = '" +clave+"';"; 
        ProductoVO producto = null;
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            int cantidadDisponible = 0;
            if(rs.next()){
                producto = new ProductoVO(rs.getString("clave"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("tipoUnidad"), rs.getInt("cantidad"), rs.getFloat("precio"));
            }
        }catch(SQLException e){
            System.out.println("Error en clase Producto_DAO_Imp, metodo existe");
            e.printStackTrace();
        }
        return producto;
    }
    public void registarCompra(CompraVO compra, int clave){
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        ConexionBD cc = new ConexionBD();
        String sqlConsulta = "SELECT * FROM compra where Hora = '"+compra.getHora()+"';";
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sqlConsulta);
            int idCompra = 0;
            CompraVO compraRegistrada = null;
            while(rs.next()){
                compraRegistrada = new CompraVO(Integer.toString(rs.getInt("idCompra")), rs.getString("fecha"), rs.getString("hora"));
                if(compraRegistrada.getHora().equals(compra.getHora())){
                    //String sqlInsert = "INSERT INTO compra_producto"
                }
                
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public boolean eliminarCantidad(String clave, int cantidad){
        boolean eliminado = false;
        Connection con = null;
        Statement stm = null;
        Statement stmUpDate = null;
        ConexionBD cc = new ConexionBD();
        ResultSet rs = null;
        int nuevaCantidad = 0;
        String sql = "SELECT * FROM productos WHERE clave ='"+clave+"';";
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            int cantidadDisponible = 0;
            rs.next();
            ProductoVO producto = new ProductoVO(rs.getString("clave"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("tipoUnidad"), rs.getInt("cantidad"), rs.getFloat("precio"));
            cantidadDisponible = producto.getCantidad();
            if(cantidad<=cantidadDisponible){
                nuevaCantidad = cantidadDisponible-cantidad;
                String sql2 = "UPDATE productos SET cantidad " + " = " + nuevaCantidad + " WHERE clave = '"+clave+"';";
                //String sql2 = "UPDATE productos SET cantidad " + " = '" + nuevaCantidad + "' WHERE clave = '"+clave+"';";
                stmUpDate = con.createStatement();
                stmUpDate.execute(sql2);  
                if(cantidad==cantidadDisponible){
                    String sqlprecioAcero = "UPDATE productos SET precio = 0 WHERE clave = '"+clave+"';";
                    stmUpDate.execute(sqlprecioAcero);
                }
            }else{
                System.out.println("ERROR.NO PUEDES ELIMINAR MÁS PRODUCTOS DE LOS QUE TIENES EN ALMACEN.");
            }
            
            con.close();
            stm.close();
            rs.close();
        }catch(SQLException e){
            System.out.println("Error en cla clase Producto_DAO_Imp, metodo eliminarCantidad");
            e.printStackTrace();
        }
        return eliminado;
    }
    @Override
    public boolean agregarCantidad(String clave, int cantidad, float precio){
        boolean agregado = false;
        Connection con = null;
        Statement stm = null;
        Statement stmUpDate = null;
        ConexionBD cc = new ConexionBD();
        ResultSet rs = null;
        float precioAntiguo = 0f;
        int nuevaCantidad = 0;
        float promedio = 0;
        String sql = "SELECT * FROM productos WHERE clave ='"+clave+"';";
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            int cantidadDisponible = 0;
            rs.next();
            ProductoVO producto = new ProductoVO(rs.getString("clave"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("tipoUnidad"), rs.getInt("cantidad"), rs.getFloat("precio"));
            cantidadDisponible = producto.getCantidad();
            nuevaCantidad = cantidadDisponible+cantidad;
            precioAntiguo = producto.getPrecio();
            promedio = ((precioAntiguo * cantidadDisponible)+(precio * cantidad))/nuevaCantidad;
            String sqlActualizarCantidad = "UPDATE productos SET cantidad " + " = '" + nuevaCantidad + "' WHERE clave = '"+clave+"';";
            String sqlActualizarPrecio = "UPDATE productos SET precio " + " = '" + promedio + "' WHERE clave = '"+clave+"';";
            stmUpDate = con.createStatement();
            stmUpDate.execute(sqlActualizarCantidad);
            stmUpDate.execute(sqlActualizarPrecio);
            con.close();;
            stm.close();;
            rs.close();
        }catch(SQLException e){
            System.out.println("Error en cla clase Producto_DAO_Imp, metodo Agregar cantidad");
            e.printStackTrace();
        }
        return agregado;
        
    }
    
}
