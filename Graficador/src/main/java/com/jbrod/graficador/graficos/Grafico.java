
package com.jbrod.graficador.graficos;

import com.jbrod.graficador.ui.VistaDeTrabajo;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author jorge
 */
public abstract class Grafico {
    
    protected Color color;
    private boolean animado; 
    private boolean lineal; 
    private int orden = 0;
    
    private int frameActual = 0;
    private int contadorFrames = 0; 
    private int[] posxAnimacion; 
    private int[] posyAnimacion;
    private int posxDestino, posyDestino; 
    
    protected int posx, posy; 
    
    public abstract void establecerGrafico(VistaDeTrabajo vista);
    public abstract void establecerGrafico(Graphics2D g2d);
    
    
    public void ejecutarAnimacion(){
        //Se ejecutaran 10 frames de la animacion
        if(contadorFrames == 0){
            //Calcular las nuevas x e y para 10 frames
            posxAnimacion = new int[10];
            posyAnimacion = new int[10];
            
            double newX = (posx-posxDestino)/10;
            double newY = (posy-posyDestino)/10;
            int newXR = (int)Math.round(newX);
            int newYR = (int)Math.round(newY);
            
            
            for (int i = 0; i < posxAnimacion.length; i++) {
                //Coordenadas en base a lineal o curva
                if(lineal){
                    //Transformar x, y en una linea
                    
                    if(i > 0){
                        posxAnimacion[i] = posxAnimacion[i-1] + newXR;
                        posyAnimacion[i] = posyAnimacion[i-1] + newYR;
                    }else{
                        posxAnimacion[i] = posx+newXR;
                        posyAnimacion[i] = posy+newYR;
                    }
                }else{
                    
                    if(i > 0){
                        int x2, y2; 
                        if(i%2 == 0){
                            x2 = newYR*3;
                            y2 = newXR*3; 
                        }else{
                            x2 = -1 *newYR*3;
                            y2 = -1*newXR*3; 
                        }
                        
                        posxAnimacion[i] = posxAnimacion[i-1] + (int) (posx + newXR * Math.cos(2 * Math.PI * i / newXR )) + x2;
                        posyAnimacion[i] = posyAnimacion[i-1] + (int) (posy + newXR * Math.cos(2 * Math.PI * i / newYR ))+y2;
                    }else{      
                        if(i > 0){
                        int x2, y2; 
                        if(i%2 == 0){
                            x2 = newYR*3;
                            y2 = newXR*3; 
                        }else{
                            x2 = -1 *newYR*3;
                            y2 = -1*newXR*3; 
                        }
                        posxAnimacion[i] = (int) (posx + newXR * Math.cos(2 * Math.PI * i / newXR )) + x2;
                        posyAnimacion[i] = (int) (posy + newXR * Math.cos(2 * Math.PI * i / newYR )) + y2;
                        
                        }
                    }
                }
            }
        }
        
        
        
        if(contadorFrames != 10 ){
          
          if(contadorFrames == 10){
              contadorFrames = posxAnimacion.length-1;
          }
          posx = posxAnimacion[contadorFrames];
          posy = posyAnimacion[contadorFrames];
            System.out.println("X: " + posx);
            System.out.println("Y: " + posy);
          contadorFrames++;

        } else if(contadorFrames == 10){
            posx = posxDestino;
            posy = posyDestino;
            //Graficar
            animado = false; 
        }
    }
    
    
    
    
    public int obtenerOrden(){
        return orden; 
    }
    
    public void setOrden(int orden){
        this.orden = orden; 
    }
    
    
    
    public void setAnimado(boolean animado){
        this.animado = animado; 
    }
    
    public boolean isAnimado(){
        return animado; 
    }
    
    public void setLineal(boolean lineal){
        this.lineal = lineal; 
    }
    
    public boolean isLineal(){
        return lineal; 
    }
    
    //Establecer las coordenadas x, y destino
    public void establecerDestino(int x, int y){
        posxDestino = x; 
        posyDestino = y; 
    }
}
