
package com.jbrod.graficador.graficos;

import com.jbrod.graficador.ui.VistaDeTrabajo;
import java.awt.Graphics;

/**
 *
 * @author jorge
 */
public class Linea extends Grafico{

    private String nombre; 
    private int posx1; 
    private int posy1; 
    private int posx2; 
    private int posy2; 
    
    @Override
    public void establecerGrafico(VistaDeTrabajo vista) {
        Graphics g = vista.obtenerGrafico();
        g.setColor(color);
        g.drawLine(posx1, posy1, posx2, posy2);
    }
    
}
