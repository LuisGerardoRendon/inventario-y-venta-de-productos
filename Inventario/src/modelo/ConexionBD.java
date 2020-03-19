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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author rendo
 */
public class ConexionBD {
    public String driver = "com.mysql.jdbc.Driver";
    public String database = "inventario";
    public String hostname = "localhost";
    public String port = "3306";
    public String url = "jdbc:mysql://"+hostname+":"+port+"/"+database+"?useSSL=false";
    public String username= "root";
    public String password="123456";
    
    
    
    public Connection conectarMySQL(){
        Connection conn = null;
        try{
            Class.forName(driver);
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-6", "root", "123456");
            System.out.println("Se establecio la conexion con la base de datos");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar base de datos");
            e.printStackTrace();;
        }
        return conn;
    }
    
}
