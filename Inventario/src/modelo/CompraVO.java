/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.Calendar;
 

/**
 *
 * @author rendon
 */
public class CompraVO {
    private String idCOmpra;
    private String fecha, hora;

    @Override
    public String toString() {
        return "CompraVO{" + "idCOmpra=" + idCOmpra + ", fecha=" + fecha + ", hora=" + hora + '}';
    }
 
    
    public CompraVO() {
        this.idCOmpra = "NULL";
        this.fecha = this.generarFecha();
        this.hora=  this.generarHora();
        
    }
 

    public CompraVO(String idCOmpra, String fecha, String hora) {
        this.idCOmpra = idCOmpra;
        this.fecha = fecha ;
        this.hora = hora ;
    }
       public String generarFecha(){
        String fecha ="";
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) +1;
        int anio = calendario.get(Calendar.YEAR);
        fecha = dia + "/"+ mes + "/" + anio;
        return fecha;
    }
       public String generarHora(){
        String hora ="";
        Calendar calendario = Calendar.getInstance();
        int hora1 =calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);
        hora = hora1 + ":"+minutos + ":" + segundos;
        return hora;
       }
    

    public void setIdCOmpra(String idCOmpra) {
        this.idCOmpra = idCOmpra;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public String getIdCOmpra() {
        return idCOmpra;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
    
}
