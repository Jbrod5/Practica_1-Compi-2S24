
package com.jbrod.graficador.ui;

import com.jbrod.graficador.reportes.ReporteAnimaciones;
import com.jbrod.graficador.reportes.ReporteColores;
import com.jbrod.graficador.reportes.ReporteErrores;
import com.jbrod.graficador.reportes.ReporteObjetos;
import com.jbrod.graficador.reportes.ReporteOperadores;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author jorge
 */
public class Reportes extends javax.swing.JFrame {

    public Reportes(ReporteOperadores operadores, ReporteColores colores, ReporteObjetos objetos, ReporteAnimaciones animaciones, ReporteErrores lexicos, ReporteErrores sintacticos) {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        //Si hay errores, solo mostrar los errores
        if(lexicos.estaVacio() && sintacticos.estaVacio()){
            //Reporte de operadores
            JTable rOperadores = new JTable(operadores.obtenerOperadores());
            pnl.add(new JLabel("Ocurrencias de operadores matematicos"));
            pnl.add(rOperadores);
            
            // Reporte de colores
            JTable rColores = new JTable(colores.obtenerReporteColores());
            pnl.add(new JLabel("Conteo de colores"));
            pnl.add(rColores);
            
            // Reporte de objetos
            JTable rObjetos = new JTable(objetos.obtenerReporteObjetos());
            pnl.add(new JLabel("Conteo de objetos"));
            pnl.add(rObjetos);
            
            // Reporte de animaciones
            JTable rAnimaciones = new JTable(animaciones.obtenerReporteAnimaciones());
            pnl.add(new JLabel("Conteo de animaciones"));
            pnl.add(rAnimaciones);

        }else{
            //Reportes de errores
            pnl.add(new JLabel("Reporte de errores"));
            JTable rErroresL = new JTable(lexicos.obtenerErrores());
            pnl.add(rErroresL);
            JTable rErroresS = new JTable(sintacticos.obtenerErrores());
            pnl.add(rErroresS);
            
        }
           

    }
    


    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pnl = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl.setLayout(new java.awt.GridLayout(11, 0));
        jScrollPane1.setViewportView(pnl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl;
    // End of variables declaration//GEN-END:variables
}
