
package com.jbrod.graficador.reportes;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class ReporteErrores {
    
    private DefaultTableModel errores;
    private boolean vacio; 
    
    public ReporteErrores(){
        errores = new DefaultTableModel();
        
        errores.addColumn("Lexema");
        errores.addColumn("Linea");
        errores.addColumn("Columna");
        errores.addColumn("Tipo");
        errores.addColumn("Descripcion");
        
        errores.addRow(new Object[]{"Lexema", "Linea", "Columna", "Tipo", "Descripcion"});
        vacio = true; 
    }
    
    public void agregarError(String lexema, int linea, int columna, String tipo, String descripcion){
        errores.addRow(new Object[]{lexema, linea, columna, tipo, descripcion});
        vacio = false; 
    }
    
    public DefaultTableModel obtenerErrores(){
        return errores;
    }
    
    public boolean estaVacio(){
        return vacio;
    }
    
}
