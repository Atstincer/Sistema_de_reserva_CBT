/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import clases.Conexion;
import java.awt.event.KeyEvent;
import java.sql.*;

/**
 *
 * @author ALEX
 */
public class Clientes_update extends javax.swing.JFrame {

    public static int Id_cliente;

    /**
     * Creates new form Clientes_agregar
     */
    public Clientes_update() {
        initComponents();
        setSize(370, 470);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Actualizar info clientes - " + Login.user);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        Id_cliente = Clientes_tablaFiltro.Id_Cliente_update;

        jButton_serviciosAsociados.setText("<html><p>Servicios</p><p>asociados</p></html>");

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select Nombre, Apellido, Nacionalidad, Telefono_fijo, Telefono_cell, "
                    + "E_mail, Observaciones from Clientes where Id_cliente = '" + Id_cliente + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                jTextField_nombre.setText(rs.getString("Nombre"));
                jTextField_apellido.setText(rs.getString("Apellido"));
                jTextField_nacionalidad.setText(rs.getString("Nacionalidad"));
                jTextField_telefono.setText(rs.getString("Telefono_fijo"));
                jTextField_cell.setText(rs.getString("Telefono_cell"));
                jTextField_mail.setText(rs.getString("E_mail"));
                jTextArea_observaciones.setText(rs.getString("Observaciones"));
            }
            cn.close();

        } catch (SQLException e) {
            System.err.println("Error al conectar para llenar los campos. " + e);
            JOptionPane.showMessageDialog(null, "!!Error al conectar con la BD...contacte al administrador.");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField_nombre = new javax.swing.JTextField();
        jTextField_apellido = new javax.swing.JTextField();
        jTextField_nacionalidad = new javax.swing.JTextField();
        jTextField_telefono = new javax.swing.JTextField();
        jTextField_cell = new javax.swing.JTextField();
        jTextField_mail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_observaciones = new javax.swing.JTextArea();
        jButton_update = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jButton_eliminar = new javax.swing.JButton();
        jButton_serviciosAsociados = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("INFORMACION GENERAL DEL CLIENTE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 29, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nombre");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 75, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Apellido");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 75, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nacionalidad");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 139, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Tel??fono");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 205, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Celular");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 269, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Mail");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 139, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Observaciones");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 205, -1, -1));

        jTextField_nombre.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_nombre.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_nombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 92, 100, 25));

        jTextField_apellido.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_apellido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_apellido.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_apellido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 92, 100, 25));

        jTextField_nacionalidad.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_nacionalidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_nacionalidad.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_nacionalidad.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_nacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nacionalidadActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_nacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 156, 100, 25));

        jTextField_telefono.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_telefono.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_telefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_telefonoActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 222, 100, 25));

        jTextField_cell.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_cell.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_cell.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_cell.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_cell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_cellActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_cell, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 286, 100, 25));

        jTextField_mail.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_mail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField_mail.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_mail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_mailActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 156, 165, 25));

        jTextArea_observaciones.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea_observaciones.setColumns(20);
        jTextArea_observaciones.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea_observaciones.setForeground(new java.awt.Color(0, 0, 115));
        jTextArea_observaciones.setRows(5);
        jTextArea_observaciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jScrollPane1.setViewportView(jTextArea_observaciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 222, -1, 89));

        jButton_update.setBackground(new java.awt.Color(153, 153, 153));
        jButton_update.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_update.setText("Actualizar");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });
        jButton_update.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_updateKeyPressed(evt);
            }
        });
        jPanel1.add(jButton_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 348, 90, 25));

        jLabel_footer.setText("?? Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, -1, 20));

        jButton_eliminar.setBackground(new java.awt.Color(153, 153, 153));
        jButton_eliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_eliminar.setText("Eliminar");
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });
        jButton_eliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_eliminarKeyPressed(evt);
            }
        });
        jPanel1.add(jButton_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 348, 80, 25));

        jButton_serviciosAsociados.setBackground(new java.awt.Color(153, 153, 153));
        jButton_serviciosAsociados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_serviciosAsociados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_serviciosAsociadosActionPerformed(evt);
            }
        });
        jButton_serviciosAsociados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_serviciosAsociadosKeyPressed(evt);
            }
        });
        jPanel1.add(jButton_serviciosAsociados, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 335, 100, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(407, 407, 407))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_nacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nacionalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nacionalidadActionPerformed

    private void jTextField_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_telefonoActionPerformed

    private void jTextField_cellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_cellActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_cellActionPerformed

    private void jTextField_mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_mailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_mailActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        // TODO add your handling code here
        Actualizar();
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed
        // TODO add your handling code here:
        Eliminar();
    }//GEN-LAST:event_jButton_eliminarActionPerformed

    private void jButton_serviciosAsociadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_serviciosAsociadosActionPerformed
        // TODO add your handling code here:
        Servicios_asociados servicios_asociados = new Servicios_asociados();
        servicios_asociados.setVisible(true);
    }//GEN-LAST:event_jButton_serviciosAsociadosActionPerformed

    private void jButton_updateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_updateKeyPressed
        // TODO add your handling code here:
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            Actualizar();
        }
    }//GEN-LAST:event_jButton_updateKeyPressed

    private void jButton_eliminarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_eliminarKeyPressed
        // TODO add your handling code here:
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            Eliminar();
        }
    }//GEN-LAST:event_jButton_eliminarKeyPressed

    private void jButton_serviciosAsociadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_serviciosAsociadosKeyPressed
        // TODO add your handling code here:
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            Servicios_asociados servicios_asociados = new Servicios_asociados();
            servicios_asociados.setVisible(true);
        }
    }//GEN-LAST:event_jButton_serviciosAsociadosKeyPressed

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
            java.util.logging.Logger.getLogger(Clientes_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes_update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_serviciosAsociados;
    private javax.swing.JButton jButton_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_observaciones;
    private javax.swing.JTextField jTextField_apellido;
    private javax.swing.JTextField jTextField_cell;
    private javax.swing.JTextField jTextField_mail;
    private javax.swing.JTextField jTextField_nacionalidad;
    private javax.swing.JTextField jTextField_nombre;
    private javax.swing.JTextField jTextField_telefono;
    // End of variables declaration//GEN-END:variables

    public void Actualizar() {
        String nombre = jTextField_nombre.getText();
        String apellido = jTextField_apellido.getText();
        String nacionalidad = jTextField_nacionalidad.getText();
        String telefono = jTextField_telefono.getText();
        String cell = jTextField_cell.getText();
        String mail = jTextField_mail.getText();
        String observaciones = jTextArea_observaciones.getText();

        try {
            if (!nombre.equalsIgnoreCase("") && !apellido.equalsIgnoreCase("")) {
                Connection cn3 = Conexion.conectar();
                PreparedStatement pst3 = cn3.prepareStatement("update Clientes set Nombre=?, Apellido=?, "
                        + "Nacionalidad=?, Telefono_fijo=?, Telefono_cell=?, E_mail=?, Observaciones=? where "
                        + "Id_cliente = '" + Id_cliente + "'");

                pst3.setString(1, nombre);
                pst3.setString(2, apellido);
                pst3.setString(3, nacionalidad);
                pst3.setString(4, telefono);
                pst3.setString(5, cell);
                pst3.setString(6, mail);
                pst3.setString(7, observaciones);

                pst3.executeUpdate();
                cn3.close();

                JOptionPane.showMessageDialog(null, "Actualizaci??n correcta.");

            } else {
                JOptionPane.showMessageDialog(null, "Debes llenar los campo nombre y apellido.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e);
            JOptionPane.showMessageDialog(null, "Error al actualizar. Contacte al administrador.");
        }
    }

    public void Eliminar() {
        int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar la informacion de este cliente?");

        if (confirmacion == JOptionPane.OK_OPTION) {
            try {
                Connection cn2 = Conexion.conectar();
                PreparedStatement pst2 = cn2.prepareStatement("delete * from Clientes where Id_cliente ="
                        + " '" + Id_cliente + "'");

                pst2.executeUpdate();

                cn2.close();
                this.dispose();

            } catch (SQLException e) {
                System.err.println("Error al intentar borrar registro: " + e);
                JOptionPane.showMessageDialog(null, "!!Error al intentar borrar registro. Contacte al administrador.");
            }
        }
    }

}
