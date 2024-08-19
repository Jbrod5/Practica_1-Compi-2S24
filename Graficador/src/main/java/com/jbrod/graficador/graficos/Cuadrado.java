
package com.jbrod.graficador.graficos;

import com.jbrod.graficador.ui.VistaDeTrabajo;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jorge
 */
public class Cuadrado extends Grafico{
    
    private String nombre; 
    private int posx;
    private int posy; 
    private int tam_lado; 
    
    public Cuadrado(String nombre, int posx, int posy, int tam_lado, Color color){
        this.nombre = nombre; 
        this.posx = posx; 
        this.posy = posy; 
        this.tam_lado = tam_lado;
        super.color = color; 
    }

    @Override
    public void establecerGrafico(VistaDeTrabajo vista) {
        Graphics g = vista.obtenerGrafico();
        //Dibujar el cuadrado
        g.setColor(color);
        //El tamanio de los lados es igual por ser cuadrado c:
        g.fillRect(posx, posy, tam_lado, tam_lado);
    }
    
    
}
