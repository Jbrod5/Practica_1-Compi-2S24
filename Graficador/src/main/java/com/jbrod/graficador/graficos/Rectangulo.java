
package com.jbrod.graficador.graficos;

import com.jbrod.graficador.ui.VistaDeTrabajo;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jorge
 */
public class Rectangulo extends Grafico{

    private String nombre; 
    private int posx; 
    private int posy; 
    private int ancho; 
    private int alto; 

    public Rectangulo(String nombre, int posx, int posy, int ancho, int alto, Color color) {
        this.nombre = nombre;
        this.posx = posx;
        this.posy = posy;
        this.ancho = ancho;
        this.alto = alto;
        this.color = color; 
    }

    
    
    
    @Override
    public void establecerGrafico(VistaDeTrabajo vista) {
        Graphics g = vista.obtenerGrafico();
        //Dibujar el rectangulo
        g.setColor(color);
        g.fillRect(posx, posx, ancho, alto);
    }
    
    
    
}
