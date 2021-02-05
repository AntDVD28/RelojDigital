/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misControles;

import java.awt.Component;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AntDVD
 */
public class HoraAlarmaPropertyEditorSupport extends PropertyEditorSupport{
    
    
    private HoraAlarmaPanel editor = null;
    
    /*
    Inicializamos la hora de la Alarma en el constructor
    */
    public HoraAlarmaPropertyEditorSupport(){
        
        this.editor = new HoraAlarmaPanel();
    }
    
    /*
     * Devuelve el editor personalizado
     */
    @Override
    public Component getCustomEditor() {
        return editor;
    }
    
    /*
     * Si el valor de la propiedad es un conjunto de valores enumerados, 
     * No es nuestro caso, devolvemos null
     */
     @Override
    public String[] getTags() {
        return null;
    }
    
    /*
     * Devuelve el valor de la propiedad
     */
    
    @Override
    public Object getValue() {
        
        if(super.getValue()==null){
            setValue(null);
        }
        
        String hora =  editor.spHora.getValue().toString();
        String minutos = editor.spMinutos.getValue().toString();
        String segundos = "00";
        
        if(hora.length()==1) hora="0"+hora;
        if(minutos.length()==1) minutos ="0"+minutos;
        
        
        String horaAlarma = hora + ":" + minutos + ":" + segundos;
        
        return horaAlarma;
    }
    
    /*
     * Asigna un valor (obtenido del panel) a la propiedad
     */
    
    @Override
    public void setValue(Object horaAlarma) {
        if(horaAlarma==null){
            horaAlarma = new String();
        }
        super.setValue(horaAlarma);

    }
    
    /*
     * Indica si se proporciona un editor personalizado 
     */
    @Override
    public boolean supportsCustomEditor() {
        return true;
    }
    
     /*
     * Devuelve el valor de la propiedad como texto
     */
    
    @Override
    public String getAsText(){
        return super.getAsText();
    }

    /*
     * Asigna una texto a la propiedad
     */
    
    @Override
    public void setAsText(String text){
        
        //COMENTARIO:
        //ESTE ES EL MÉTODO QUE NO ME FUNCIONA DEL TODO, CREO YO.
        //SI NO INTRODUCES LOS SEGUNDOS VERAS COMO SE PONEN AUTOMATICAMENTE, ESTO LO HAGO CON LAS SIGUIENTES LINEAS
        //PERO ES COMO SI NO SE GUARDASE O SE ME RESETEARA POR LOS VALORES 00:00:00
        
        //Separamos el strig recibido para poder completarlo con ceros en el caso que sea necesario
        String[] parts = text.split(":");
        String part1 = parts[0];
        String part2 = parts[1];
        
        if (part1.length()==1) part1="0"+part1;
        if (part2.length()==1) part2="0"+part2;
        
        //Vuelvo a componer el string
        text = part1 + ":" + part2 + ":" + "00";
        
        super.setAsText(text);
            
    }
    
    /*
     * Este método se usa cuando se debe generar código Java
     * para establecer el valor de la propiedad. Debe devolver
     * un fragmento de código Java que se puede utilizar para 
     * inicializar una variable con el valor de la propiedad actual.
     */
    @Override
    public String getJavaInitializationString() {
        
        String hora = editor.spHora.getValue().toString();
        String minutos = editor.spMinutos.getValue().toString();
        
        if(hora.length()==1) hora="0"+hora;
        if(minutos.length()==1) minutos ="0"+minutos;
        
        String horaAlarma = "\""+hora + ":" + minutos+":"+"00"+"\"";
        
        return horaAlarma;
        
    }
    

}
