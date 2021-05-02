/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conexion;
import clases.Util;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.write.WriteException;

/**
 *
 * @author ALEX
 */
public class Reporte_venta_diaria extends javax.swing.JDialog {

    private String nombre, apellido;
    private DefaultTableModel model;

    /**
     * Creates new form Reporte_venta
     */
    public Reporte_venta_diaria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(800, 700);
        setResizable(false);
        setTitle("Reporte de ventas - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        LocalDate today = LocalDate.now();
        DateTimeFormatter dtf_formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleDateFormat sdf_formato = new SimpleDateFormat("dd/MM/yyyy");
        String today_str = today.format(dtf_formato);
        try {
            Date today_date = sdf_formato.parse(today_str);
            jDateChooser.setDate(today_date);
        } catch (ParseException e) {
            System.err.println("Error: " + e);
        }

        Color colorJDateFondo = new Color(204, 204, 204);
        ((JTextField) jDateChooser.getDateEditor().getUiComponent()).setBackground(colorJDateFondo);

        try {//recuperando nombre y apellido de usuario/vendedor
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select Nombre, Apellido from usuarios where Usuario = '"
                    + "" + Login.user + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("Nombre");
                apellido = rs.getString("Apellido");
            }
//            System.out.println("Nombre: " + nombre + ", apellido: " + apellido);
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }

        jLabel_header.setText("REPORTE VENTA                            vendedor: " + nombre + " " + apellido);

        model = new DefaultTableModel();
        jTable_venta = new JTable(model);
        jScrollPane1.setViewportView(jTable_venta);
        jTable_venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        String[] nombre_column = new String[10];
        nombre_column[0] = "TE";
        nombre_column[1] = "EMISION";
        nombre_column[2] = "EJECUCION";
        nombre_column[3] = "PAX";
        nombre_column[4] = "NOMBRE";
        nombre_column[5] = "EXCURSION";
        nombre_column[6] = "TOTAL";
        nombre_column[7] = "EFECTIVO";
        nombre_column[8] = "CREDITO";
        nombre_column[9] = "PROVEEDOR";

        for (int i = 0; i < nombre_column.length; i++) {
            model.addColumn(nombre_column[i]);
        }

//        model.addColumn("TE");
//        model.addColumn("EMISION");
//        model.addColumn("EJECUCION");
//        model.addColumn("PAX");
//        model.addColumn("NOMBRE");
//        model.addColumn("EXCURSION");
//        model.addColumn("TOTAL");
//        model.addColumn("EFECTIVO");
//        model.addColumn("CREDITO");      
        Filtrar();

        int[] ancho = new int[10];
        ancho[0] = 100;//TE
        ancho[1] = 60;//EMISION
        ancho[2] = 100;//EJECUCION
        ancho[3] = 70;//PAX
        ancho[4] = 150;//NOMBRE
        ancho[5] = 200;//EXCURSION
        ancho[6] = 70;//TOTAL
        ancho[7] = 70;//EFECTIVO
        ancho[8] = 70;//CREDITO
        ancho[9] = 90;//PROVEEDOR

        for (int i = 0; i < nombre_column.length; i++) {
            TableColumn columna = jTable_venta.getColumn(nombre_column[i]);
            Util.setAnchoColumna(columna, ancho[i]);
        }

        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(model);
        jTable_venta.setRowSorter(elQueOrdena);

        Color myColorFondo = new Color(204, 204, 204);
        Color myColorFuente = new Color(0, 0, 115);
        jTable_venta.setBackground(myColorFondo);
        jTable_venta.setForeground(myColorFuente);

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
        jLabel_header = new javax.swing.JLabel();
        jButton_filtrar = new javax.swing.JButton();
        jLabel_footer2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_venta = new javax.swing.JTable();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jButton_ReporteExcel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_header.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jLabel_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 43, -1, -1));

        jButton_filtrar.setBackground(new java.awt.Color(153, 153, 153));
        jButton_filtrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_filtrar.setText("Filtrar");
        jButton_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_filtrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_filtrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 600, 80, 30));

        jLabel_footer2.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 650, 150, 20));

        jTable_venta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable_venta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_venta.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_venta.setCellSelectionEnabled(true);
        jTable_venta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable_venta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 770, 500));

        jDateChooser.setBackground(new java.awt.Color(153, 153, 153));
        jDateChooser.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 130, 30));

        jButton_ReporteExcel.setBackground(new java.awt.Color(153, 153, 153));
        jButton_ReporteExcel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_ReporteExcel.setText("Excel");
        jButton_ReporteExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ReporteExcelActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_ReporteExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 600, 80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_filtrarActionPerformed
        // TODO add your handling code here:
        Util.VaciarTabla(model);
        Filtrar();
    }//GEN-LAST:event_jButton_filtrarActionPerformed

    private void jButton_ReporteExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ReporteExcelActionPerformed
        // TODO add your handling code here:
        Date date = jDateChooser.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoTab = new SimpleDateFormat("d MMMM");
        String fecha = formato.format(date);
        String fechaTab = formatoTab.format(date);

        String encabezado_uno = "REPORTE VENTA DIARIA                         Fecha: " + fecha;
        String footer_uno = "Vendedor: " + nombre + " " + apellido;

        File file = null;
        try {
            JFileChooser find = new JFileChooser();
            find.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.xls", "xls");
            find.setFileFilter(filtro);
            if (JFileChooser.APPROVE_OPTION == find.showSaveDialog(this)) {
                file = find.getSelectedFile();
                if (!filtro.accept(file)) {
                    String s = file.getAbsolutePath();
                    if (file.exists()) {
                        file.delete();
                    }
                    file = new File(file.getAbsolutePath() + ".xls");
                    file.createNewFile();
                } else if (!(find.getSelectedFile()).exists()) {
                    file.createNewFile();
                }

                try {
                    Util excel_exporter = new Util();
                    excel_exporter.exportToEXEL(jTable_venta, null, file, fechaTab, "", encabezado_uno, "", footer_uno, "");
                } catch (WriteException ex) {
//                        Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Error: " + ex);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo crear el archivo " + file.getAbsolutePath());
            // Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_ReporteExcelActionPerformed

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
            java.util.logging.Logger.getLogger(Reporte_venta_diaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporte_venta_diaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporte_venta_diaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporte_venta_diaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Reporte_venta_diaria dialog = new Reporte_venta_diaria(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton_ReporteExcel;
    private javax.swing.JButton jButton_filtrar;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel_footer2;
    private javax.swing.JLabel jLabel_header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_venta;
    // End of variables declaration//GEN-END:variables

    public void Filtrar() {
        Date date = jDateChooser.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(date);

//        System.out.println("Fecha en método: " + fecha);
//        System.out.println("Tabla en método rowCount: " + jTable_venta.getRowCount());
//        System.out.println("Tabla en método columnCount: " + jTable_venta.getColumnCount());
//        System.out.println("Usuario en método: " + Login.user);
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select TE_alojamiento, TE_traslado, Fecha_inicio, Fecha_fin, "
                    + "Pax, Menores, Infantes, Nombre_cliente, Apellido_cliente, Descripcion, Efectivo_hotel, "
                    + "Credito_hotel, Efectivo_trf, Credito_trf, Hotel, Destino, Proveedor_trf from servicios where "
                    + "Reservado_por = '" + Login.user + "' and Reservado_fecha = '" + fecha + "'");

            ResultSet rs = pst.executeQuery();

            double total_efectivo = 0, total_credito = 0, total_gral = 0;

            while (rs.next()) {
                Object[] fila_rs = new Object[17];
                Object[] fila_aloj = new Object[10];
                Object[] fila_trf = new Object[10];
//                int adulto = 0, menor = 0, infante = 0;
                String hotel = "", destino = "";

                for (int i = 0; i < fila_rs.length; i++) {//llenando vector fila_rs con resultados del ResultSet
                    fila_rs[i] = rs.getObject(i + 1);
                }

                if (!fila_rs[0].equals("")) {//llenando fila_aloj si TE_aloj diferente de ""
                    fila_aloj[0] = fila_rs[0];//TE aloj
                    fila_aloj[1] = fecha.substring(0, 5);//emisión
                    if (fila_rs[9].equals("CANCELADO")) {
                        fila_aloj[2] = "";//ejecución
                        fila_aloj[3] = "";//pax
                        fila_aloj[4] = "";//nombre
                        fila_aloj[5] = "CANCELADO";//Excursion (descripcion)
                        fila_aloj[6] = "0,0";//total
                        fila_aloj[7] = "0,0";//efectivo
                        fila_aloj[8] = "0,0";//crédito
                        fila_aloj[9] = "";//proveedor
                    } else {
                        fila_aloj[2] = String.valueOf(fila_rs[2]).substring(0, 5) + " al " + String.valueOf(fila_rs[3]).substring(0, 5);
                        fila_aloj[3] = fila_rs[4].toString();
                        if (!fila_rs[5].toString().equals("0")) {
                            fila_aloj[3] += " + " + fila_rs[5].toString();
                        }
                        if (!fila_rs[6].toString().equals("0")) {
                            fila_aloj[3] += " + " + fila_rs[6].toString() + " bb";
                        }
                        fila_aloj[4] = fila_rs[7] + " " + fila_rs[8];
                        fila_aloj[5] = fila_rs[9] + " " + fila_rs[14];
                        fila_aloj[6] = String.valueOf((double) fila_rs[10] + (double) fila_rs[11]).replace(".", ",");
                        total_efectivo += (double) fila_rs[10];
                        total_credito += (double) fila_rs[11];
                        total_gral += (double) fila_rs[10] + (double) fila_rs[11];
                        fila_aloj[7] = String.valueOf(fila_rs[10]).replace(".", ",");
                        fila_aloj[8] = String.valueOf(fila_rs[11]).replace(".", ",");
                        fila_aloj[9] = fila_rs[14];
                    }
                    model.addRow(fila_aloj);
                }

                if (!fila_rs[1].equals("")) {//llenando fila_trf si TE_trf diferente de ""
                    fila_trf[0] = fila_rs[1];//TE trf
                    fila_trf[1] = fecha.substring(0, 5);//emisión
                    if (fila_rs[9].equals("CANCELADO")) {
                        fila_trf[2] = "";//ejecución
                        fila_trf[3] = "";//pax
                        fila_trf[4] = "";//nombre
                        fila_trf[5] = "CANCELADO";//Excursión (descripción)
                        fila_trf[6] = "0,0";//total
                        fila_trf[7] = "0,0";//efectivo
                        fila_trf[8] = "0,0";//crédito
                        fila_trf[9] = "";//proveedor
                    } else {
                        fila_trf[2] = String.valueOf(fila_rs[2]).substring(0, 5) + " al " + String.valueOf(fila_rs[3]).substring(0, 5);
                        fila_trf[3] = fila_rs[4].toString();
                        if (!fila_rs[5].toString().equals("0")) {
                            fila_trf[3] += " + " + fila_rs[5].toString();
                        }
                        if (!fila_rs[6].toString().equals("0")) {
                            fila_trf[3] += " + " + fila_rs[6].toString() + " bb";
                        }
                        fila_trf[4] = fila_rs[7] + " " + fila_rs[8];
                        fila_trf[5] = "Traslado - " + fila_rs[15];
                        fila_trf[6] = String.valueOf((double) fila_rs[12] + (double) fila_rs[13]).replace(".", ",");
                        total_efectivo += (double) fila_rs[12];
                        total_credito += (double) fila_rs[13];
                        total_gral += (double) fila_rs[12] + (double) fila_rs[13];
                        fila_trf[7] = String.valueOf(fila_rs[12]).replace(".", ",");
                        fila_trf[8] = String.valueOf(fila_rs[13]).replace(".", ",");
                        fila_trf[9] = fila_rs[16];
                    }
                    model.addRow(fila_trf);
                }
            }

            Object[] fila = new Object[10];
            fila[0] = "Observaciones:";
            fila[6] = String.valueOf(total_gral).replace('.', ',');
            fila[7] = String.valueOf(total_efectivo).replace('.', ',');
            fila[8] = String.valueOf(total_credito).replace('.', ',');
            model.addRow(fila);
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error al llenar tabla. " + e);
            JOptionPane.showMessageDialog(null, "!!Error al mostrar tabla...contacte al administrador");
        }
    }
}