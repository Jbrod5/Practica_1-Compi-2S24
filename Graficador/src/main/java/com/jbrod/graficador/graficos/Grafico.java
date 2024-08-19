
package com.jbrod.graficador.graficos;

import com.jbrod.graficador.ui.VistaDeTrabajo;
import java.awt.Color;

/**
 *
 * @author jorge
 */
public abstract class Grafico {
    
    protected Color color;
    private boolean animado; 
    private boolean aimacionLineal; 
    
    private int frameActual = 0;
    
    public abstract void establecerGrafico(VistaDeTrabajo vista);
    
}
