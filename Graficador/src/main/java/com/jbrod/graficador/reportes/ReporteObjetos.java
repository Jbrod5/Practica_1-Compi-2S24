
package com.jbrod.graficador.reportes;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class ReporteObjetos {
    private int circulo     = 0 ; 
    private int cuadrado    = 0 ; 
    private int rectangulo  = 0 ; 
    private int linea       = 0 ; 
    private int poligono    = 0 ; 
    
    public DefaultTableModel obtenerReporteObjetos(){
        DefaultTableModel objetos = new DefaultTableModel();
        
        objetos.addColumn("Objeto");
        objetos.addColumn("Cantidad de usos");
        
        objetos.addRow(new Object[]{"Objeto", "Cantidad de usos"});
        objetos.addRow(new Object[]{"circulo"   , circulo   });
        objetos.addRow(new Object[]{"cuadrado"  , cuadrado  });
        objetos.addRow(new Object[]{"rectangulo", rectangulo});
        objetos.addRow(new Object[]{"linea"     , linea     });
        objetos.addRow(new Object[]{"poligono"  , poligono  });
        
        return objetos; 
    }
    
    public void aumentarCirculo(){
        circulo++;
    }
    
    public void aumentarCuadrado(){
        cuadrado++;
    }
    
    public void aumentarRectangulo(){
        rectangulo++;
    }
    
    public void aumentarLinea(){
        linea++;
    }
    
    public void aumentarPoligono(){
        poligono++;
    }
}
