/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appGraficaAdministrador;

import appGraficaCliente.FrmReservar_Comprar;

/**
 *
 * @author steve
 */
public class FrmCine extends javax.swing.JFrame {

    /**
     * Creates new form FrmCinepolis
     */
    public FrmCine() {
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMnuIniciarSesion = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMnuConsultarCartelera = new javax.swing.JMenuItem();
        jMnuReservarComprar = new javax.swing.JMenuItem();
        jMnuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jMenu1.setText("Administrar Cartelera");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jMnuIniciarSesion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMnuIniciarSesion.setText("Iniciar sesión");
        jMnuIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuIniciarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(jMnuIniciarSesion);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Reservaciones y compras");
        jMenu3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jMnuConsultarCartelera.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMnuConsultarCartelera.setText("Consultar Cartelera");
        jMnuConsultarCartelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuConsultarCarteleraActionPerformed(evt);
            }
        });
        jMenu3.add(jMnuConsultarCartelera);

        jMnuReservarComprar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMnuReservarComprar.setText("Reservar y comprar");
        jMnuReservarComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuReservarComprarActionPerformed(evt);
            }
        });
        jMenu3.add(jMnuReservarComprar);

        jMnuSalir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMnuSalir.setText("Salir del sistema");
        jMnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuSalirActionPerformed(evt);
            }
        });
        jMenu3.add(jMnuSalir);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 884, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMnuIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuIniciarSesionActionPerformed
        PrincipalCine.frmLogin.setVisible(true);
    }//GEN-LAST:event_jMnuIniciarSesionActionPerformed

    private void jMnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMnuSalirActionPerformed

    private void jMnuReservarComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuReservarComprarActionPerformed
        PrincipalCine.frmReservarComprar = new FrmReservar_Comprar();
        PrincipalCine.frmReservarComprar.setLocationRelativeTo(null);
        PrincipalCine.frmReservarComprar.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnuReservarComprarActionPerformed

    private void jMnuConsultarCarteleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuConsultarCarteleraActionPerformed
        PrincipalCine.jdlgConsultarCartelera.setVisible(true);

    }//GEN-LAST:event_jMnuConsultarCarteleraActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCine().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMnuConsultarCartelera;
    private javax.swing.JMenuItem jMnuIniciarSesion;
    private javax.swing.JMenuItem jMnuReservarComprar;
    private javax.swing.JMenuItem jMnuSalir;
    // End of variables declaration//GEN-END:variables
}
