
package com.jbrod.graficador.reportes;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class ReporteOperadores {
    private DefaultTableModel operadores; 
    
    public ReporteOperadores(){
        operadores = new DefaultTableModel();
        
        operadores.addColumn("Operador");
        operadores.addColumn("Linea");
        operadores.addColumn("Columna");
        operadores.addColumn("Ocurrencia");
        
        operadores.addRow(new Object[]{"Operador", "Linea", "Columna", "Ocurrencia"});
    }
    
    public void agregarOcurrencia(String operador, int linea, int columna, String ocurrencia){
        operadores.addRow(new Object[]{operador, linea, columna, ocurrencia});
    }
    
    public DefaultTableModel obtenerOperadores(){
        return operadores; 
    }
}
