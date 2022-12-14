/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appGraficaAdministrador;

import capaLogica.Cine;
import capaLogica.Cliente;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author steve
 */
public class JDlgEstadosSalas extends javax.swing.JDialog {

    /**
     * Creates new form JDlgEstadosSalas
     */
    public JDlgEstadosSalas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarJTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCboSalas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblSalas = new javax.swing.JTable();
        jLblSala = new javax.swing.JLabel();

        setTitle("Estado de las salas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Seleccione la sala que desea visualizar su estado:");

        jCboSalas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jCboSalas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sala 1", "Sala 2", "Sala 3" }));
        jCboSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboSalasActionPerformed(evt);
            }
        });

        jTblSalas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTblSalas);

        jLblSala.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLblSala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblSala.setText("Mostrando el estado de la Sala 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel1)
                                .addGap(57, 57, 57)
                                .addComponent(jCboSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1054, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLblSala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCboSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLblSala)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened
    private void cargarJTable() {
        String[] encabezados = {"Fila/Columna", "1", "2", "3", "4", "5", "6", "7", "8"};
        String[] columnas = new String[9];

        String letras = "ABCDEFGHIJ";

        int sala = jCboSalas.getSelectedIndex();

        // Se define el modelo de la tabla para poder hacer el binding "enlace" con los datos de la matriz
        DefaultTableModel modeloTabla = new DefaultTableModel(encabezados, 0);
        jTblSalas.setModel(modeloTabla);

        Cliente[][][] matriz = Cine.getMatrizSalas();

        for (int i = 0; i < matriz[sala].length; i++) {
            columnas[0] = String.valueOf(letras.charAt(i));
            for (int j = 0; j < matriz[sala][i].length; j++) {
                if (matriz[sala][i][j] == null) {
                    columnas[j + 1] = "\u25A0";

                } else {
                    columnas[j + 1] = "\u25CF";
                }
            }
            modeloTabla.addRow(columnas);
            columnas = new String[9];
        }
        jTblSalas.setFont(new Font("Century Gothic", Font.BOLD, 14));
        jTblSalas.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 18));
        jTblSalas.getTableHeader().setForeground(Color.green);
        jTblSalas.setRowHeight(30);
    }
    private void jCboSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboSalasActionPerformed
        // TODO add your handling code here:
        jLblSala.setText("Mostrando el estado de la " + (String) jCboSalas.getSelectedItem());
        cargarJTable();
    }//GEN-LAST:event_jCboSalasActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgEstadosSalas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgEstadosSalas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgEstadosSalas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgEstadosSalas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgEstadosSalas dialog = new JDlgEstadosSalas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jCboSalas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLblSala;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblSalas;
    // End of variables declaration//GEN-END:variables
}
