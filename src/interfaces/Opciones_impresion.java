/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conexion;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEX
 */
public class Opciones_impresion extends javax.swing.JFrame {

    String ida_origen, ida_hora, regreso_origen, regreso_hora;

    /**
     * Creates new form PDF_opciones_impresion
     */
    public Opciones_impresion() {
        initComponents();
        setSize(400, 360);
        setResizable(false);
        setTitle("Detalles de impresión - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select * from datos_impresion");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ida_origen = rs.getString("ida_origen");
                ida_hora = rs.getString("ida_hora");
                regreso_origen = rs.getString("regreso_origen");
                regreso_hora = rs.getString("regreso_hora");
            }

            jTextField_ida_origen.setText(ida_origen);
            jTextField_ida_hora.setText(ida_hora);
            jTextField_regreso_origen.setText(regreso_origen);
            jTextField_regreso_hora.setText(regreso_hora);

            cn.close();

        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/Icon.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_ida_origen = new javax.swing.JTextField();
        jTextField_ida_hora = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_regreso_hora = new javax.swing.JTextField();
        jTextField_regreso_origen = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel_footer = new javax.swing.JLabel();
        jButton_actualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Opciones de impresión");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 25, -1, -1));

        jTextField_ida_origen.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_ida_origen.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_ida_origen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_ida_origen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 125, 250, -1));

        jTextField_ida_hora.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_ida_hora.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_ida_hora.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_ida_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 95, 60, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Datos IDA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel3.setText("Hora:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 95, -1, -1));

        jLabel4.setText("Lugar:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 125, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Datos REGRESO");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel6.setText("Hora:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 195, -1, -1));

        jTextField_regreso_hora.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_regreso_hora.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_regreso_hora.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_regreso_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 195, 60, -1));

        jTextField_regreso_origen.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_regreso_origen.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_regreso_origen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_regreso_origen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_regreso_origenKeyPressed(evt);
            }
        });
        jPanel1.add(jTextField_regreso_origen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 225, 250, -1));

        jLabel7.setText("Lugar:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 225, -1, -1));

        jLabel_footer.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 150, 20));

        jButton_actualizar.setBackground(new java.awt.Color(153, 153, 153));
        jButton_actualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_actualizar.setText("Actualizar");
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        jButton_actualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_actualizarKeyPressed(evt);
            }
        });
        jPanel1.add(jButton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed
        // TODO add your handling code here:
        Actualizar();
    }//GEN-LAST:event_jButton_actualizarActionPerformed

    private void jButton_actualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_actualizarKeyPressed
        // TODO add your handling code here:
        if(evt.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            Actualizar();
        }
    }//GEN-LAST:event_jButton_actualizarKeyPressed

    private void jTextField_regreso_origenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_regreso_origenKeyPressed
        // TODO add your handling code here:
        if(evt.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            Actualizar();
        }
    }//GEN-LAST:event_jTextField_regreso_origenKeyPressed

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
            java.util.logging.Logger.getLogger(Opciones_impresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Opciones_impresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Opciones_impresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Opciones_impresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Opciones_impresion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_ida_hora;
    private javax.swing.JTextField jTextField_ida_origen;
    private javax.swing.JTextField jTextField_regreso_hora;
    private javax.swing.JTextField jTextField_regreso_origen;
    // End of variables declaration//GEN-END:variables

    public void Actualizar() {
        ida_hora = jTextField_ida_hora.getText().toString();
        ida_origen = jTextField_ida_origen.getText().toString();
        regreso_hora = jTextField_regreso_hora.getText().toString();
        regreso_origen = jTextField_regreso_origen.getText().toString();

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("update datos_impresion set ida_origen=?, ida_hora=?, "
                    + "regreso_origen=?, regreso_hora=?");

            pst.setString(1, ida_origen);
            pst.setString(2, ida_hora);
            pst.setString(3, regreso_origen);
            pst.setString(4, regreso_hora);

            pst.executeUpdate();

            cn.close();
            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");
            this.dispose();

        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }

}
