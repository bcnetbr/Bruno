/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conversao;

/**
 *
 * @author bcnet
 */
public class Conversor {
    
    public static long converteParaSegundos(String tempo)
    {
        String[] array = tempo.split(":");
        long segundos = Long.parseLong(array[0])*3600 + Long.parseLong(array[1]) * 60 + Long.parseLong(array[2]) * 1;
        return segundos;
    }
    
    public static String converteParaHorasMinutosSegundos(long segundos)
    {
        long horas = segundos/3600;
        long minutos = (segundos-(horas * 3600))/60;
        long segundos2 = segundos - (horas * 3600)-(minutos * 60);
        
        StringBuilder x = new StringBuilder();
        x.append(String.format("%02d", horas));
        x.append(":");
        x.append(String.format("%02d", minutos));
        x.append(":");
        x.append(String.format("%02d", segundos2));
        
        return x.toString();
    }
    

    
}
