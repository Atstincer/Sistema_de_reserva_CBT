/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conexion;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ALEX
 */
public class Reporte_venta_periodo extends javax.swing.JFrame {

    /**
     * Creates new form PDF_opciones_impresion
     */
    public Reporte_venta_periodo() {
        initComponents();
        setSize(340, 235);
        setResizable(false);
        setTitle("Reporte de venta - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        Color colorJDateFondo = new Color(204,204,204);        
        ((JTextField)jDateChooser_desde.getDateEditor().getUiComponent()).setBackground(colorJDateFondo);
        ((JTextField)jDateChooser_hasta.getDateEditor().getUiComponent()).setBackground(colorJDateFondo);  

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
        jLabel_footer = new javax.swing.JLabel();
        jButton_calcular = new javax.swing.JButton();
        jDateChooser_desde = new com.toedter.calendar.JDateChooser();
        jDateChooser_hasta = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Calcular ventas para el período:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 25, -1, -1));

        jLabel_footer.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 180, 150, 20));

        jButton_calcular.setBackground(new java.awt.Color(153, 153, 153));
        jButton_calcular.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_calcular.setText("Calcular");
        jButton_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_calcularActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_calcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 145, 80, -1));

        jDateChooser_desde.setBackground(new java.awt.Color(153, 153, 153));
        jDateChooser_desde.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateChooser_desde, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 85, 130, 30));

        jDateChooser_hasta.setBackground(new java.awt.Color(153, 153, 153));
        jDateChooser_hasta.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateChooser_hasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 85, 130, 30));

        jLabel2.setText("Desde");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, -1, -1));

        jLabel3.setText("Hasta");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 65, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_calcularActionPerformed
        // TODO add your handling code here:    

        Date desde = jDateChooser_desde.getDate();
        Date hasta_date = jDateChooser_hasta.getDate();

        if (desde == null || hasta_date == null) {
            JOptionPane.showMessageDialog(null, "Debe selecionar las fechas que desea.");
        } else {
            DateTimeFormatter formato_DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DecimalFormat df = new DecimalFormat("#.00");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String desde_string = sdf.format(desde);
//            System.out.println("desde_string: " + desde_string);
            LocalDate dia_recorrido_LocalDate = LocalDate.parse(desde_string, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//            System.out.println("Dia_recorrido_LocalDate: " + dia_recorrido_LocalDate);

            String hasta_string = sdf.format(hasta_date);
            LocalDate hasta_LocalDate = LocalDate.parse(hasta_string, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//            System.out.println("Hasta_LocalDate: " + hasta_LocalDate);

            double totalEfectivo = 0, totalCredito = 0;
            String nombre = "", apellido = "";

            try {
                Connection cn = Conexion.conectar();
                while (dia_recorrido_LocalDate.isBefore(hasta_LocalDate.plusDays(1))) {
                    PreparedStatement pst = cn.prepareStatement("select Efectivo_hotel, Efectivo_trf, Credito_hotel, Credito_trf "
                            + "from servicios where Reservado_fecha = '" + dia_recorrido_LocalDate.format(formato_DTF) + "' "
                            + "and Reservado_por = '" + Login.user + "'");

                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        totalEfectivo += rs.getDouble("Efectivo_hotel");
                        totalEfectivo += rs.getDouble("Efectivo_trf");
                        totalCredito += rs.getDouble("Credito_hotel");
                        totalCredito += rs.getDouble("Credito_trf");
                    }

//                    System.out.println(dia_recorrido_LocalDate);
                    dia_recorrido_LocalDate = dia_recorrido_LocalDate.plusDays(1);
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

            JOptionPane.showMessageDialog(null, "Venta de " + nombre + " " + apellido + " desde " + desde_string + " "
                    + "hasta " + hasta_string + "\n\n"
                    + "-en efectivo: " + totalEfectivo_string + "\n" + ""
                    + "-en tarjeta de crédito: " + totalCredito_string + "\n" + ""
                    + "-para un total de: " + total_string, "VENTA SEGUN PERIODO - " + Login.user, ICONIFIED);
        }


    }//GEN-LAST:event_jButton_calcularActionPerformed

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
            java.util.logging.Logger.getLogger(Reporte_venta_periodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporte_venta_periodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporte_venta_periodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporte_venta_periodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reporte_venta_periodo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_calcular;
    private com.toedter.calendar.JDateChooser jDateChooser_desde;
    private com.toedter.calendar.JDateChooser jDateChooser_hasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
