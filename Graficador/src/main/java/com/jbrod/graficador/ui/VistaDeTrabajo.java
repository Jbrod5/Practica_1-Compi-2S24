package com.jbrod.graficador.ui;

import com.jbrod.graficador.analizadores.Lexer;
import com.jbrod.graficador.analizadores.Parser;
import com.jbrod.graficador.graficos.Grafico;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.StyledEditorKit;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import javax.swing.JPanel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.util.Comparator;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;

/**
 *
 * @author jorge
 */
public class VistaDeTrabajo extends javax.swing.JPanel {

    private VentanaPrincipal principal; 
    private String nombreArchivo; 
    private String rutaArchivo; 
    private File archivo; 
    private LinkedList<Grafico> graficos;
    private Grafico ultimo; // Ultimo grafico agregado
    
    
    private Parser parser; 
    private Lexer lexer; 
    
    public VistaDeTrabajo(VentanaPrincipal principal) {
        initComponents();
        graficos = new LinkedList<>();
        
        this.principal = principal;
        Graphics g = pnlGraficos.getGraphics();
        pnlGraficos.paint(g);
    }
    
    public VistaDeTrabajo(VentanaPrincipal principal, String ruta){
        initComponents();
        Graphics g = pnlGraficos.getGraphics();
        pnlGraficos.paint(g);
        
        this.principal = principal; 
        
        this.rutaArchivo = ruta; 
        archivo = new File(ruta);
        nombreArchivo = archivo.getName();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(archivo))){
            StringBuilder contenido = new StringBuilder();
            String linea; 
            while((linea = reader.readLine()) != null){
                contenido.append(linea).append("\n");
            }
            
            txpnCodigo.setText(contenido.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    
    
    
    
    // Metodos para graficar figuras
    public void agregarFigura(Grafico grafico){
        graficos.add(grafico);
        ultimo = grafico; 
    }
    
    public void agregarAnimacionUltimaFigura(boolean lineal, int x, int y, int orden){
        if(ultimo != null){
            ultimo.establecerDestino(x, y);
            ultimo.setAnimado(true);
            ultimo.setLineal(lineal);
            
            ultimo.setOrden(orden);
        }
        
    }
    
    public void instanciarGraficos(){
        StringReader reader = new StringReader(txpnCodigo.getText());
        lexer = new Lexer(reader);
        parser = new Parser(lexer, this);
        parser.setVista(this);
        
        try {
            //Si no hay errores, dibujar
            parser.parse();
            dibujarGraficos();
        } catch (Exception ex) {
            // Si hay errores, no hacer nada
            // Se dibuja de momento para pruebas
            Logger.getLogger(VistaDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
            dibujarGraficos();
        }
        
        
    }
    
    public void dibujarGraficos(){
        ////pnlGraficos.repaint();
        pnlGraficos.paint(pnlGraficos.getGraphics());
        for (Grafico grafico : graficos) {
            grafico.establecerGrafico(this);
            System.out.println(grafico);
            
            //dibujarLinea(0, 0, 0, 0, Color.black);
        }
    }
    
    private void ordenarAnimacione(){
        /*int n = graficos.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1-i; j++) {
                if(graficos.get(j).obtenerOrden() > graficos.get(j+1).obtenerOrden()){
                    //intercambiar
                    Grafico temp = graficos.get(j);
                    graficos.remove(j);
                    graficos.add(graficos.get(j+1), temp);
                }
            }
            
        }*/
    }
    
    public void ejecutarAnimaciones(){
        //Ordenar las animaciones
        Collections.sort(graficos, Comparator.comparingInt(Grafico::obtenerOrden));
        
        //pnlGraficos.paint(g);
        for (Grafico grafico : graficos) {
            while(grafico.isAnimado()){
                grafico.ejecutarAnimacion();
                dibujarGraficos();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VistaDeTrabajo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    
    
    
    public void dibujarLinea(int x1, int y1, int x2, int y2, Color color){
        Graphics g = pnlGraficos.getGraphics();
        //pnlGraficos.paint(g);
        g.setColor(color);
        Random random = new Random();
        x1 = random.nextInt(300)+1;
        y1 = random.nextInt(300)+1;
        x2 = random.nextInt(300)+1;
        y2 = random.nextInt(300)+1;
        //x1, y1, x2, y2
        g.drawLine(x1, y1, x2, y2);
        //pnlGraficos.paint(g);*/
        
    }
    

    
    
    public void exportarPanelAPNG(String rutaArchivo) {
        // Crear un BufferedImage
        rutaArchivo +="/" + nombreArchivo + ".png";
        BufferedImage imagen = new BufferedImage(pnlGraficos.getWidth(), pnlGraficos.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = imagen.createGraphics();
        //Dibujar en g2d
        for (Grafico grafico : graficos) {
            grafico.establecerGrafico(g2d);
        }
        
        // Dibujar el JPanel en el BufferedImage
        /*Graphics g = imagen.getGraphics();
        pnlGraficos.paint(g);
        dibujarGraficos();
        g.dispose();*/
        
        // Guardar la imagen como archivo PNG
        try {
            ImageIO.write(imagen, "png", new File(rutaArchivo));
            System.out.println("El panel se ha exportado a " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public void exportarAPDF(String rutaArchivo) {
        // Renderizar el JPanel a una imagen
         exportarPanelAPNG(rutaArchivo);
         String rutaimg =  rutaArchivo +"/" + nombreArchivo + ".png";
         rutaArchivo +="/" + nombreArchivo + ".pdf";
         
         
        // Crear un PDF usando Apache PDFBox
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDImageXObject pdImage = PDImageXObject.createFromFile(rutaimg, document);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(pdImage, 20, 20);
            contentStream.close();
            document.save(rutaArchivo);
            System.out.println("PDF creado exitosamente en: " + rutaArchivo);
            //Eliminar png
            File img = new File(rutaimg);
            img.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCompilar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txpnCodigo = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlGraficos = new javax.swing.JPanel();
        btnAnimar = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        chbsPDF = new javax.swing.JCheckBox();

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Codigo"));
        jScrollPane1.setViewportView(txpnCodigo);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Graficos"));

        javax.swing.GroupLayout pnlGraficosLayout = new javax.swing.GroupLayout(pnlGraficos);
        pnlGraficos.setLayout(pnlGraficosLayout);
        pnlGraficosLayout.setHorizontalGroup(
            pnlGraficosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        pnlGraficosLayout.setVerticalGroup(
            pnlGraficosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlGraficos);

        jSplitPane1.setRightComponent(jScrollPane2);

        btnAnimar.setText("Animar");
        btnAnimar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnimarActionPerformed(evt);
            }
        });

        btnReportes.setText("Reportes");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        chbsPDF.setText("PDF");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnimar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReportes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chbsPDF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addGap(3, 3, 3)
                .addComponent(btnCerrar)
                .addContainerGap())
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnGuardar)
                    .addComponent(btnCerrar)
                    .addComponent(btnAnimar)
                    .addComponent(btnReportes)
                    .addComponent(btnExportar)
                    .addComponent(chbsPDF))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        principal.cerrarPestana(this);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Si tiene una ruta, solamente guardar
        if(rutaArchivo != null && !rutaArchivo.isEmpty()){
            guardarContenido();
        }else{
            //Obtener la ruta donde guardar
            JFileChooser fileChooser = new JFileChooser();
            // Mostrar el cuadro de diálogo para abrir un archivo
            int returnValue = fileChooser.showOpenDialog(null);
            // Verificar si el usuario seleccionó un archivo
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Obtener el archivo seleccionado
                File selectedFile = fileChooser.getSelectedFile();
                // Obtener la ruta del archivo
                rutaArchivo = selectedFile.getAbsolutePath();
                 
                //Agregar la vista
                principal.remove(this);
                nombreArchivo = selectedFile.getName();
                principal.agregarPestana(this, nombreArchivo);
            
                System.out.println("Ruta del archivo seleccionado: " + rutaArchivo);
                guardarContenido();
            } else {
                System.out.println("No se seleccionó ningún archivo.");
            }
            //Volver a cargar la ventana con el titulo
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        // TODO add your handling code here:
        /*Random random = new Random();
        int x1 = random.nextInt(300)+1;
        int y1 = random.nextInt(300)+1;
        int x2 = random.nextInt(300)+1;
        int y2 = random.nextInt(300)+1;
        dibujarLinea(x1, y1, x2, y2, Color.black);*/
        
        //Limpiar la lista de graficos
        graficos = new LinkedList<>();
        graficos.clear();
        //Parsear
        instanciarGraficos();
        dibujarGraficos();
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnAnimarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnimarActionPerformed
        // TODO add your handling code here:
        ejecutarAnimaciones();
    }//GEN-LAST:event_btnAnimarActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
        if(parser != null){
            Reportes r = new Reportes(parser.obtenerReporteOperadores(),
                                      parser.obtenerReporteColores(),
                                      parser.obtenerReporteObjetos(),
                                      parser.obtenerReporteAnimaciones(), 
                                      lexer.obtenerReporteErrores(), 
                                      parser.obtenerReporteErrores());
            r.setVisible(true);
        }
        
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        
        // 1. OBTENER LA RUTA DE LA CARPETA ------------------------------------------------------------------------------------------------
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar Carpeta");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Solo permite seleccionar carpetas
        String carpeta = "";
        // Mostrar el cuadro de diálogo
        int resultado = chooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File carpetaSeleccionada = chooser.getSelectedFile(); // Obtener la carpeta seleccionada
            carpeta =  carpetaSeleccionada.getAbsolutePath();
            System.out.println("Ruta de la carpeta seleccionada: " + carpetaSeleccionada.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ninguna carpeta.");
        }
        
        // 2. SI ES PNG: 
        if(!chbsPDF.isSelected()){
            exportarPanelAPNG(carpeta);
        }else{
        //3 Si es PDF
            exportarAPDF(carpeta);
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void guardarContenido(){
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Escribir el nuevo contenido en el archivo
            writer.write(txpnCodigo.getText());
            System.out.println("Contenido reemplazado exitosamente.");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
        
    }
    
    public Graphics obtenerGrafico(){
        return pnlGraficos.getGraphics();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnimar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnReportes;
    private javax.swing.JCheckBox chbsPDF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel pnlGraficos;
    private javax.swing.JTextPane txpnCodigo;
    // End of variables declaration//GEN-END:variables
}
