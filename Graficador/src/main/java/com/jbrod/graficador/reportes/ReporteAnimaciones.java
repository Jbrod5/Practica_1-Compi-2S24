
package com.jbrod.graficador.reportes;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class ReporteAnimaciones {
    private int linea = 0; 
    private int curva = 0; 
    
    
    public DefaultTableModel obtenerReporteAnimaciones(){
        DefaultTableModel animaciones = new DefaultTableModel();
        
        animaciones.addColumn("Animacion");
        animaciones.addColumn("Cantidad de usos");
        
        animaciones.addRow(new Object[]{"Animacion", "Cantidad de usos"});
        animaciones.addRow(new Object[]{"linea", linea});
        animaciones.addRow(new Object[]{"curva", curva});
        
        return animaciones; 
    }
    
    
    public void aumentarLinea(){
        linea++;
    }
    
    public void aumentarCurva(){
        curva++;
    }
}
