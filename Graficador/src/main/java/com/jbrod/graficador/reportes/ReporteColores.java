
package com.jbrod.graficador.reportes;

import javax.swing.table.DefaultTableModel;
/**
 *
 * @author jorge
 */
public class ReporteColores {
    private int azul        = 0 ; 
    private int rojo        = 0 ; 
    private int amarillo    = 0 ; 
    private int verde       = 0 ;
    private int morado      = 0 ; 
    private int naranja     = 0 ;     
    private int turquesa    = 0 ; 
    private int negro       = 0 ; 
    private int cafe        = 0 ;
    
    public DefaultTableModel obtenerReporteColores(){
        DefaultTableModel colores = new DefaultTableModel();
        
        colores.addColumn("Color");
        colores.addColumn("Cantidad de usos");
        
        colores.addRow(new Object[]{"Color", "Cantidad de usos"});
        colores.addRow(new Object[]{"azul"      , azul      });
        colores.addRow(new Object[]{"rojo"      , rojo      });
        colores.addRow(new Object[]{"amarillo"  , amarillo  });
        colores.addRow(new Object[]{"verde"     , verde     });
        colores.addRow(new Object[]{"morado"    , morado    });
        colores.addRow(new Object[]{"naranja"   , naranja   });
        colores.addRow(new Object[]{"turquesa"  , turquesa  });
        colores.addRow(new Object[]{"negro"     , negro     });
        colores.addRow(new Object[]{"cafe"      , cafe      });
        
        
        return colores; 
    }
    
    public void aumentarAzul(){
        azul++;
    }
    public void aumentarRojo(){
        rojo++; 
    }
    public void aumentarAmarillo(){
        amarillo++;
    }
    public void aumentarVerde(){
        verde++;
    }
    public void aumentarMorado(){
        morado++;
    }
    public void aumentarNaranja(){
        naranja++;
    }
    public void aumentarTurquesa(){
        turquesa++;
    }
    public void aumentarNegro(){
        negro++;
    }
    public void aumentarCafe(){
        cafe++;
    }
}
