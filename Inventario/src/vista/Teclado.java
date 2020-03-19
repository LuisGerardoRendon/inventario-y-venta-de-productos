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
import java.util.Scanner;

/**
 *
 * @author rendo
 */
public class Teclado {
    public int leerEntero(){
        int entero = 0;
        Scanner sc = new Scanner(System.in);
        try{
            entero = sc.nextInt();
        }catch(Exception e){
            System.out.println("Error al leer el dato");
        }
        return entero;
    }
    public String leerCadena(){
        String cadena = "";
        Scanner sc = new Scanner (System.in);
        try{
            cadena = sc.nextLine();
        }catch(Exception e){
            System.out.println("Error al leer el dato");
        }
        return cadena;
    }
    public float leerFloat(){
        float flotante = 0;
        Scanner sc = new Scanner(System.in);
        try{
            flotante  = sc.nextFloat();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error al leer el dato");
        }
        return flotante;
    }
    
}
