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
public class Compra_DA0_Imp implements Compra_DAO{

    @Override
    public List<CompraVO> readAll() {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        ConexionBD cc = new ConexionBD();
        String sql = "SELECT * FROM Compra ORDER BY idCompra;";
        List<CompraVO>lista = new ArrayList<CompraVO>();
        System.out.println("Entra a readALL");
        
        try{
            con = cc.conectarMySQL();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                System.out.println("TIENE REGISTROS COMPRA");
                CompraVO compra = new CompraVO(Integer.toString(rs.getInt("idCompra")), rs.getString("fecha"), rs.getString("Hora"));
                lista.add(compra);
                System.out.println("El idCOMPRA ES:"+compra.getIdCOmpra() +"FECHA:" +compra.generarFecha() + "HORA: " + compra.getHora());
            }
            con.close();
            stm.close();
            rs.close();
            
            
        }catch(SQLException e){
            System.out.println("EEROR en clase Compra_DAO_Imp, Metodo readAll !!!!!");
            e.printStackTrace();
        }
        return lista;
    }
    public CompraVO rescatarCompra(String idCompra){
        CompraVO compra = null;
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        ConexionBD cc = new ConexionBD();
        String sql = "SELECT * FROM Compra WHERE idCompra = " + idCompra;
        try{
            con = cc.conectarMySQL();
            stm =con.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            compra = new CompraVO(Integer.toString(rs.getInt("idCompra")), rs.getString("fecha"), rs.getString("Hora"));
            System.out.println("LAS COMPRA RECUPERADA ES:");
            System.out.println(compra.toString());
            con.close();
            stm.close();
            rs.close();
            
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("ERROR en la clase Compra_DAO_Imp, Método rescatarCompra");
        }
        return compra;
    }
    
    
}
