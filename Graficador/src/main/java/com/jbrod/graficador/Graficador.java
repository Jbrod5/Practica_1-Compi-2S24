
package com.jbrod.graficador;

import com.jbrod.graficador.analizadores.Lexer;
import com.jbrod.graficador.analizadores.Parser;
import com.jbrod.graficador.ui.VentanaPrincipal;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jorge
 */
public class Graficador {

    public static void main(String[] args) {

        try {
            System.out.println("olaolaolaaaa");
            
            VentanaPrincipal principal = new VentanaPrincipal();
            
            String s = "graficar poligono ( PoligA, 12 + 2, 15, 6, 50 / 2*(12-8/2), 12, amarillo)"
                    +  "graficar circulo (figura_1, 25 + 12, 25, 15/3, rojo)"
                    +  "graficar linea (linea_Amarillo, 12 * 3 + 2, 15, 4 / 4, 50 * 1, amarillo)"
                    +  "graficar rectangulo (rectangulo_12, 12 * 3 + 2, 15, 4 / 4, negro)";
            StringReader reader = new StringReader(s);
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
            parser.parse();
        } catch (Exception ex) {
            Logger.getLogger(Graficador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
