/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conexion;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEX
 */
public class Vendedores extends javax.swing.JFrame {

    /**
     * Creates new form AdminIII
     */
    public Vendedores() {
        initComponents();
        setSize(700, 700);
        setResizable(false);
        setTitle("Sesión VENDEDORES - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       


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
        jLabel_logo = new javax.swing.JLabel();
        jLabel_footer = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_opciones = new javax.swing.JMenu();
        jMenuItem_cambiar_contrasena = new javax.swing.JMenuItem();
        jMenuItem_salir = new javax.swing.JMenuItem();
        jMenu_serv = new javax.swing.JMenu();
        jMenuItem_reservarServ = new javax.swing.JMenuItem();
        jMenuItem_filtroServ = new javax.swing.JMenuItem();
        jMenuItem_clientes = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem_paraTransportistas = new javax.swing.JMenuItem();
        jMenuItem_origen_hora_trf = new javax.swing.JMenuItem();
        jMenuItem_liquidacion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem_Venta_mes_actual = new javax.swing.JMenuItem();
        jMenuItem_Venta_Anio_hasta_actualidad = new javax.swing.JMenuItem();
        jMenuItem_Venta_segun_periodo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cubaturin.png"))); // NOI18N
        jPanel1.add(jLabel_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 200, 300));

        jLabel_footer.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 600, 150, 20));

        jMenu_opciones.setText("Opciones");

        jMenuItem_cambiar_contrasena.setText("Cambiar contraseña");
        jMenuItem_cambiar_contrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_cambiar_contrasenaActionPerformed(evt);
            }
        });
        jMenu_opciones.add(jMenuItem_cambiar_contrasena);

        jMenuItem_salir.setText("Salir");
        jMenuItem_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_salirActionPerformed(evt);
            }
        });
        jMenu_opciones.add(jMenuItem_salir);

        jMenuBar1.add(jMenu_opciones);

        jMenu_serv.setText("Servicios");

        jMenuItem_reservarServ.setText("Reservar");
        jMenuItem_reservarServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_reservarServActionPerformed(evt);
            }
        });
        jMenu_serv.add(jMenuItem_reservarServ);

        jMenuItem_filtroServ.setText("Consulta - Modificar/Actualizar");
        jMenuItem_filtroServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_filtroServActionPerformed(evt);
            }
        });
        jMenu_serv.add(jMenuItem_filtroServ);

        jMenuItem_clientes.setText("Clientes");
        jMenuItem_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_clientesActionPerformed(evt);
            }
        });
        jMenu_serv.add(jMenuItem_clientes);

        jMenuBar1.add(jMenu_serv);

        jMenu1.setText("Reportes");

        jMenu3.setText("Para Transportista");

        jMenuItem_paraTransportistas.setText("Reporte");
        jMenuItem_paraTransportistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_paraTransportistasActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem_paraTransportistas);

        jMenuItem_origen_hora_trf.setText("Editar origen/hora traslados");
        jMenuItem_origen_hora_trf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_origen_hora_trfActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem_origen_hora_trf);

        jMenu1.add(jMenu3);

        jMenuItem_liquidacion.setText("Liquidación");
        jMenuItem_liquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_liquidacionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_liquidacion);

        jMenu2.setText("Estadísticas VENTA");

        jMenuItem_Venta_mes_actual.setText("En lo que va de mes");
        jMenuItem_Venta_mes_actual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_Venta_mes_actualActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_Venta_mes_actual);

        jMenuItem_Venta_Anio_hasta_actualidad.setText("En lo que va de año");
        jMenuItem_Venta_Anio_hasta_actualidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_Venta_Anio_hasta_actualidadActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_Venta_Anio_hasta_actualidad);

        jMenuItem_Venta_segun_periodo.setText("Según período seleccionado");
        jMenuItem_Venta_segun_periodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_Venta_segun_periodoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_Venta_segun_periodo);

        jMenu1.add(jMenu2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_clientesActionPerformed
        // TODO add your handling code here:

        Clientes_tablaFiltro clientes = new Clientes_tablaFiltro();
        clientes.setVisible(true);

        /*
        Clientes_Admin clientes = new Clientes_Admin();
        
        int width = jDesktopPane1.getWidth();
        int height = jDesktopPane1.getHeight();
        clientes.setSize(width, height);
        
        jDesktopPane1.add(clientes);
        
        /*
        clientes.toFront();
        clientes.setLocation(jDesktopPane1.getWidth() / 2 - clientes.getWidth() / 2,jDesktopPane1.getWidth() / 2 - clientes.getWidth() / 2);
        jDesktopPane1.add(clientes);*/
 /*
        try {
            clientes.setMaximum(true);
        } catch (PropertyVetoException e) {
            System.out.println("Error en maximizar internalframe" + e);
        }*/
//        clientes.setVisible(true);

    }//GEN-LAST:event_jMenuItem_clientesActionPerformed

    private void jMenuItem_reservarServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_reservarServActionPerformed
        // TODO add your handling code here:
        Servicios_reservar servicios_reservar = new Servicios_reservar();
        servicios_reservar.setVisible(true);
    }//GEN-LAST:event_jMenuItem_reservarServActionPerformed

    private void jMenuItem_filtroServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_filtroServActionPerformed
        // TODO add your handling code here:
        Servicios_tablaFiltro servicios_tablaFiltro = new Servicios_tablaFiltro();
        servicios_tablaFiltro.setVisible(true);
    }//GEN-LAST:event_jMenuItem_filtroServActionPerformed

    private void jMenuItem_paraTransportistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_paraTransportistasActionPerformed
        // TODO add your handling code here:
        Reporte_paraTransportista pdf_paraTransportista = new Reporte_paraTransportista();
        pdf_paraTransportista.setVisible(true);
    }//GEN-LAST:event_jMenuItem_paraTransportistasActionPerformed

    private void jMenuItem_origen_hora_trfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_origen_hora_trfActionPerformed
        // TODO add your handling code here:
        Opciones_impresion opciones = new Opciones_impresion();
        opciones.setVisible(true);
    }//GEN-LAST:event_jMenuItem_origen_hora_trfActionPerformed

    private void jMenuItem_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_liquidacionActionPerformed
        // TODO add your handling code here:
        Reporte_liquidacion liquidacion = new Reporte_liquidacion();
        liquidacion.setVisible(true);
    }//GEN-LAST:event_jMenuItem_liquidacionActionPerformed

    private void jMenuItem_Venta_mes_actualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_Venta_mes_actualActionPerformed
        // TODO add your handling code here:
        LocalDate hoy = LocalDate.now();
        LocalDate dia_recorrido = hoy.with(TemporalAdjusters.firstDayOfMonth());

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#.00");

        double totalEfectivo = 0, totalCredito = 0;
        String nombre = "", apellido = "";

        try {
            Connection cn = Conexion.conectar();
            while (dia_recorrido.isBefore(hoy.plusDays(1))) {
                PreparedStatement pst = cn.prepareStatement("select Efectivo_hotel, Efectivo_trf, Credito_hotel, Credito_trf "
                        + "from servicios where Reservado_fecha = '" + dia_recorrido.format(formato) + "' and Reservado_por = '"
                        + "" + Login.user + "'");

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    totalEfectivo += rs.getDouble("Efectivo_hotel");
                    totalEfectivo += rs.getDouble("Efectivo_trf");
                    totalCredito += rs.getDouble("Credito_hotel");
                    totalCredito += rs.getDouble("Credito_trf");
                }

//                System.out.println(dia);
                dia_recorrido = dia_recorrido.plusDays(1);                
            }

            PreparedStatement pst = cn.prepareStatement("select Nombre, Apellido from usuarios where Usuario = '"
                    + "" + Login.user + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("Nombre");
                apellido = rs.getString("Apellido");
            }
            cn.close();
        } catch (SQLException e) {
        }
        
        String totalEfectivo_string = "";
        String totalCredito_string = "";
        String total_string = "";
        
        if (totalEfectivo == 0) {
            totalEfectivo_string = "0,00";
        } else {
            totalEfectivo_string = df.format(totalEfectivo);
        }
        
        if (totalCredito == 0) {
            totalCredito_string = "0,00";
        } else {
            totalCredito_string = df.format(totalCredito);
        }
        
        if ((totalEfectivo + totalCredito) == 0) {
            total_string = "0,00";
        } else {
            total_string = df.format(totalEfectivo + totalCredito);
        }
        
        JOptionPane.showMessageDialog(null, "Venta de " + nombre + " " + apellido + " en lo que va de mes hasta hoy "
                + "" + hoy.format(formato) + "\n\n" + ""
                + "-en efectivo: " + totalEfectivo_string + "\n" + ""
                + "-en tarjeta de crédito: " + totalCredito_string + "\n" + ""
                + "-para un total de: " + total_string, "VENTA MES ACTUAL - " + Login.user, ICONIFIED);        

    }//GEN-LAST:event_jMenuItem_Venta_mes_actualActionPerformed

    private void jMenuItem_Venta_Anio_hasta_actualidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_Venta_Anio_hasta_actualidadActionPerformed
        // TODO add your handling code here:
        LocalDate hoy = LocalDate.now();
        LocalDate dia_recorrido = hoy.with(TemporalAdjusters.firstDayOfYear());

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#.00");

        double totalEfectivo = 0, totalCredito = 0;
        String nombre = "", apellido = "";

        try {
            Connection cn = Conexion.conectar();
            while (dia_recorrido.isBefore(hoy.plusDays(1))) {
                PreparedStatement pst = cn.prepareStatement("select Efectivo_hotel, Efectivo_trf, Credito_hotel, Credito_trf "
                        + "from servicios where Reservado_fecha = '" + dia_recorrido.format(formato) + "' and Reservado_por = '"
                        + "" + Login.user + "'");

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    totalEfectivo += rs.getDouble("Efectivo_hotel");
                    totalEfectivo += rs.getDouble("Efectivo_trf");
                    totalCredito += rs.getDouble("Credito_hotel");
                    totalCredito += rs.getDouble("Credito_trf");
                }

//                System.out.println(dia);
                dia_recorrido = dia_recorrido.plusDays(1);                
            }

            PreparedStatement pst = cn.prepareStatement("select Nombre, Apellido from usuarios where Usuario = '"
                    + "" + Login.user + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("Nombre");
                apellido = rs.getString("Apellido");
            }
            cn.close();
        } catch (SQLException e) {
        }
        
        String totalEfectivo_string = "";
        String totalCredito_string = "";
        String total_string = "";
        
        if (totalEfectivo == 0) {
            totalEfectivo_string = "0,00";
        } else {
            totalEfectivo_string = df.format(totalEfectivo);
        }
        
        if (totalCredito == 0) {
            totalCredito_string = "0,00";
        } else {
            totalCredito_string = df.format(totalCredito);
        }
        
        if ((totalEfectivo + totalCredito) == 0) {
            total_string = "0,00";
        } else {
            total_string = df.format(totalEfectivo + totalCredito);
        }
        
        JOptionPane.showMessageDialog(null, "Venta de " + nombre + " " + apellido + " en lo que va de año hasta hoy "
                + "" + hoy.format(formato) + "\n\n" + ""
                + "-en efectivo: " + totalEfectivo_string + "\n" + ""
                + "-en tarjeta de crédito: " + totalCredito_string + "\n" + ""
                + "-para un total de: " + total_string, "VENTA DEL AÑO - " + Login.user, ICONIFIED); 
        
    }//GEN-LAST:event_jMenuItem_Venta_Anio_hasta_actualidadActionPerformed

    private void jMenuItem_Venta_segun_periodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_Venta_segun_periodoActionPerformed
        // TODO add your handling code here:
        Reporte_venta_periodo rvp = new Reporte_venta_periodo();
        rvp.setVisible(true);
    }//GEN-LAST:event_jMenuItem_Venta_segun_periodoActionPerformed

    private void jMenuItem_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_salirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItem_salirActionPerformed

    private void jMenuItem_cambiar_contrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_cambiar_contrasenaActionPerformed
        // TODO add your handling code here:
        ContrasenaCambiar cc = new ContrasenaCambiar();
        cc.setVisible(true);
    }//GEN-LAST:event_jMenuItem_cambiar_contrasenaActionPerformed

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
            java.util.logging.Logger.getLogger(Vendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_Venta_Anio_hasta_actualidad;
    private javax.swing.JMenuItem jMenuItem_Venta_mes_actual;
    private javax.swing.JMenuItem jMenuItem_Venta_segun_periodo;
    private javax.swing.JMenuItem jMenuItem_cambiar_contrasena;
    private javax.swing.JMenuItem jMenuItem_clientes;
    private javax.swing.JMenuItem jMenuItem_filtroServ;
    private javax.swing.JMenuItem jMenuItem_liquidacion;
    private javax.swing.JMenuItem jMenuItem_origen_hora_trf;
    private javax.swing.JMenuItem jMenuItem_paraTransportistas;
    private javax.swing.JMenuItem jMenuItem_reservarServ;
    private javax.swing.JMenuItem jMenuItem_salir;
    private javax.swing.JMenu jMenu_opciones;
    private javax.swing.JMenu jMenu_serv;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}