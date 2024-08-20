
package com.jbrod.graficador.graficos;

import com.jbrod.graficador.ui.VistaDeTrabajo;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jorge
 */
public class Poligono extends Grafico{

    private String nombre;
    private int cooX[];
    private int cooY[];
    private int nLados;
    private int ancho; 
    private int alto; 

    public Poligono(String nombre, int posx, int posy, int nLados, int ancho, int alto, Color color) {
        this.nombre = nombre;
        this.posx = posx;
        this.posy = posy;
        this.nLados = nLados;
        
        this.ancho = ancho; 
        this.alto = alto; 
        this.color = color;
        
        cooX = new int[nLados];
        cooY = new int [nLados];
        calcularPol();
        
    }
    
    public void calcularPol(){
        for (int i = 0; i < nLados; i++) {
            cooX[i] = (int) (posx + ancho * Math.cos(2 * Math.PI * i / nLados)); // Coordenada x
            cooY[i] = (int) (posy + alto  * Math.sin(2 * Math.PI * i / nLados)); // Coordenada y
        }
    }
    
    
    
    
    @Override
    public void establecerGrafico(VistaDeTrabajo vista) {
        Graphics g = vista.obtenerGrafico();
        calcularPol();
        g.setColor(color);
        g.fillPolygon(cooX, cooY, nLados);
    }
    
}
