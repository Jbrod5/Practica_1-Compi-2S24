
package com.jbrod.graficador.graficos;

import com.jbrod.graficador.ui.VistaDeTrabajo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author jorge
 */
public class Circulo extends Grafico{

    private String nombre; 
    private int radio; 
    
    public Circulo(String nombre, int posx, int posy, int radio, Color color){
        this.nombre = nombre; 
        this.posx = posx; 
        this.posy = posy; 
        this.radio = radio; 
        this.color = color;
    }
    
    @Override
    public void establecerGrafico(VistaDeTrabajo vista) {
        Graphics g = vista.obtenerGrafico();
        //Dibujar el circulo
        g.setColor(color);
        //El ancho y el alto es el doble del radio
        g.fillOval(posx, posy, 2*radio, 2*radio);
    }

    @Override
    public void establecerGrafico(Graphics2D g2d) {
        //Dibujar el circulo
        g2d.setColor(color);
        //El ancho y el alto es el doble del radio
        g2d.fillOval(posx, posy, 2*radio, 2*radio);
    }
    
}
