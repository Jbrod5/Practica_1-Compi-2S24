
package com.jbrod.graficador.graficos;

import com.jbrod.graficador.ui.VistaDeTrabajo;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jorge
 */
public class Linea extends Grafico{

    private String nombre; 
    private int posx2; 
    private int posy2; 

    public Linea(String nombre, int posx1, int posy1, int posx2, int posy2, Color color) {
        this.nombre = nombre;
        this.posx = posx1;
        this.posy = posy1;
        this.posx2 = posx2;
        this.posy2 = posy2;
        this.color = color; 
    }
    
    
    
    @Override
    public void establecerGrafico(VistaDeTrabajo vista) {
        Graphics g = vista.obtenerGrafico();
        g.setColor(color);
        g.drawLine(posx, posy, posx2, posy2);
    }
    
}
