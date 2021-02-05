/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misControles;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author AntDVD
 */
public class ComponenteRelojDJR2 extends JLabel implements Serializable{
    
    private boolean formato24 = true;
    private String horaAlarma;
       
    private SimpleDateFormat sdf24h = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat sdf12h = new SimpleDateFormat("hh:mm:ss a");
    
    private HoraAlarmaListener hal;
    
    public ComponenteRelojDJR2(){
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            
            @Override
            public void run(){
                Date hora = new Date();
                String horaActual;
                if(formato24){
                    setText(sdf24h.format(hora));
                    horaActual =sdf24h.format(hora);
                }
                    
                else {
                    setText(sdf12h.format(hora));
                    horaActual = sdf12h.format(hora);
                }
                    
                if(horaAlarma!=null){
                    
                    if(horasCoinciden(horaActual, horaAlarma)){
                        
                        //System.out.println("Las horas coinciden");
                        
                        if( hal != null ){
                            hal.suenaAlarma();
                        }
                    }
                }
            }
                  
        }, 0, 1000);  
        
    }

    public boolean isFormato24() {
        return formato24;
    }

    public void setFormato24(boolean formato24) {
        this.formato24 = formato24;
    }
    
    public String getHoraAlarma() {
        return horaAlarma;
    }

    public void setHoraAlarma(String horaAlarma) {
        this.horaAlarma = horaAlarma;
    }
    
    private boolean horasCoinciden(String hora, String alarma){
        
       //System.out.println(hora);
       //System.out.println(alarma);
        
       //Guardo horas y minutos en variables separadas para poder compararlas
       String[] partesHoraActual = hora.split(":");
       String horaActual = partesHoraActual[0];
       String minutosActual = partesHoraActual[1];
       String segundosActual = partesHoraActual[2];
       //En el caso que el formato sea de 12 horas los segundos están representados con los dos primeros caracteres
       if(segundosActual.length()>2)
           segundosActual = segundosActual.substring(0,2);
       
       
       String[] partesHoraAlarma = alarma.split(":");
       String horaAlarma = partesHoraAlarma[0];
       String minutosAlarma = partesHoraAlarma[1];
       String segundosAlarma = partesHoraAlarma[2];
       
       
       //Comparo las horas y minutos
       if( horaActual.equals(horaAlarma) && minutosActual.equals(minutosAlarma) && segundosActual.equals(segundosAlarma) )
           return true;
       else
           return false;
    }

    //Método a través del cual obtenemos la implementación del método
    public void addHoraAlarmaListener(HoraAlarmaListener hal){
        this.hal = hal;
    }
    
    
    
}
