/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.blackhorse.kronos.frames;

import com.blackhorse.kronos.Functions;
import java.util.List; 

/**
 *
 * @author whitesnake
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class MergeFrame extends javax.swing.JFrame {
    
    
    private final List<File> pdfFiles;
    DefaultListModel<String> model = new DefaultListModel<>();
    Functions fun = new Functions();
    private static MergeFrame instancia;

    /**
     * Creates new form MergeFrame
     */
    public MergeFrame() {
        this.pdfFiles = new ArrayList<>();
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

        uploadFilesButton = new javax.swing.JButton();
        mergeFilesButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        fileListToMerge = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Unir PDF");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        uploadFilesButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        uploadFilesButton.setText("Cargar archivos");
        uploadFilesButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        uploadFilesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        uploadFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadFilesButtonActionPerformed(evt);
            }
        });

        mergeFilesButton.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        mergeFilesButton.setText("Unir y descargar");
        mergeFilesButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mergeFilesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mergeFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mergeFilesButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(fileListToMerge);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uploadFilesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mergeFilesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(uploadFilesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mergeFilesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mergeFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mergeFilesButtonActionPerformed
        if (!pdfFiles.isEmpty()) {
            PDFMergerUtility pdfMerger = new PDFMergerUtility();
            for (File pdf : pdfFiles) {
                try {
                    pdfMerger.addSource(pdf);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "Error: "+ioException);
                }
            }
            pdfMerger.setDestinationFileName("Kronos combinado "+fun.getDate()+".pdf");
            try {
                pdfMerger.mergeDocuments(null);
                JOptionPane.showMessageDialog(null, "Archivos PDF combinados exitosamente!");
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error: "+ioException);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se han cargado archivos PDF.");
        }
    }//GEN-LAST:event_mergeFilesButtonActionPerformed

    private void uploadFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadFilesButtonActionPerformed
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
                    fileListToMerge.setModel(model);
                    model.addElement(file.toString());
                }else{
                    JOptionPane.showMessageDialog(null, "Formato de archivo incorrecto!");
                }
            }
        }
    }//GEN-LAST:event_uploadFilesButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MergeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MergeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MergeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MergeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MergeFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> fileListToMerge;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mergeFilesButton;
    private javax.swing.JButton uploadFilesButton;
    // End of variables declaration//GEN-END:variables

    public static MergeFrame getInstancia(){
        if (instancia == null){
            instancia = new MergeFrame();
            instancia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            instancia.setVisible(true);
            
        }
        return instancia;
    }
    
}
