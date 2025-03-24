/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.blackhorse.kronos.frames;

import com.blackhorse.kronos.Functions;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;


/**
 *
 * @author whitesnake
 */
public class CompressFrame extends javax.swing.JFrame {
    
    private final List<File> pdfFiles;
    DefaultListModel<String> model = new DefaultListModel<>();
    Functions fun = new Functions();
    private static CompressFrame instancia;

    /**
     * Creates new form CompressFrame
     */
    public CompressFrame() {
        this.pdfFiles = new ArrayList<>();
        initComponents();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uploadComButton = new javax.swing.JButton();
        convertComButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        fileListCom = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Comprimir PDF");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        uploadComButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        uploadComButton.setText("Cargar archivo");
        uploadComButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        uploadComButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        uploadComButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadComButtonActionPerformed(evt);
            }
        });

        convertComButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        convertComButton.setText("Comprimir PDF");
        convertComButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        convertComButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        convertComButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertComButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(fileListCom);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uploadComButton, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(convertComButton, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(uploadComButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(convertComButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadComButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadComButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        
        fileChooser.setFileFilter(new FileFilter() {
            public String getDescription() {
                return "PDF Files (*.pdf)";
            }
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".pdf") ;
                }
            }
        });
        
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            for (File file : selectedFiles) {
                if (file.getName().endsWith(".pdf")) {
                    pdfFiles.add(file);
                    fileListCom.setModel(model);
                    model.addElement(file.toString());
                }else{
                    JOptionPane.showMessageDialog(null, "Formato de archivo incorrecto!");
                }
            }
        }
    }//GEN-LAST:event_uploadComButtonActionPerformed

    private void convertComButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertComButtonActionPerformed
        if (!pdfFiles.isEmpty()) {

            for (File pdf : pdfFiles) {

                try {
                    PdfReader reader = new PdfReader(pdf.getAbsolutePath());
                    PdfWriter writer = new PdfWriter("Kronos comprimido "+fun.getDate()+
                            ".pdf", new com.itextpdf.kernel.pdf.WriterProperties().setCompressionLevel(9));
                    // Opcional: Ajustar la información del documento
                    try (PdfDocument pdfDoc = new PdfDocument(reader, writer)) {
                        // Opcional: Ajustar la información del documento
                        PdfDocumentInfo info = pdfDoc.getDocumentInfo();
                        info.setTitle("Archivo Comprimido");
                        info.setAuthor("Tu Nombre");
                        
                        // Ajustar la compresión de las imágenes
                        pdfDoc.getCatalog().put(PdfName.Perms, new PdfNumber(9));
                    }
                    JOptionPane.showMessageDialog(null, "Archivos PDF comprimido exitosamente!");
                    System.out.println("Compresión completada.");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error: "+e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se han cargado archivos PDF.");
        }
    }//GEN-LAST:event_convertComButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        instancia = null;
        dispose();
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CompressFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompressFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompressFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompressFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompressFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton convertComButton;
    private javax.swing.JList<String> fileListCom;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton uploadComButton;
    // End of variables declaration//GEN-END:variables

    public static CompressFrame getInstancia(){
        if (instancia == null){
            instancia = new CompressFrame();
            instancia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            instancia.setVisible(true);
            
        }
        return instancia;
    }

}
