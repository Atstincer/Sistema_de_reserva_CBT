/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conexion;
import clases.Util;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jxl.write.WriteException;

/**
 *
 * @author ALEX
 */
public class Reporte_liquidacion extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    String nombre, apellido;
    

    /**
     * Creates new form Clientes_AdminII
     */
    public Reporte_liquidacion() {
        initComponents();
        setSize(800, 700);
        setResizable(false);
        setTitle("Liquidación - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        String dia = Integer.toString(calendar.get(Calendar.DATE));
        String mes = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String anio = Integer.toString(calendar.get(Calendar.YEAR));
        String fecha = dia + "/" + mes + "/" + anio;

//        System.out.println("dia: " + dia + "\nmes: " + mes + "\naño: " + anio + "\nFecha: " + fecha + "\n");
        
        try {
            Date date = formato.parse(fecha);
            jDateChooser.setDate(date);
        } catch (ParseException e) {
        }
        
        Color colorJDateFondo = new Color(204,204,204);        
        ((JTextField)jDateChooser.getDateEditor().getUiComponent()).setBackground(colorJDateFondo);
        
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
        }

        jLabel_header.setText("LIQUIDACION                              vendedor: " + nombre + " " + apellido);
        
        jTable_venta = new JTable(model);
        jScrollPane1.setViewportView(jTable_venta);
        
        model.addColumn("TE");
        model.addColumn("ACTIVIDAD");
        model.addColumn("PAX");
        model.addColumn("EFECTIVO");
        model.addColumn("TARJETA CREDITO");
        model.addColumn("CREDITO");
        model.addColumn("TOTAL");

        Filtrar();
        
        TableColumn columnaTE = jTable_venta.getColumn("TE");
        columnaTE.setMaxWidth(90);
        columnaTE.setMinWidth(90);
        TableColumn columnaPax = jTable_venta.getColumn("PAX");
        columnaPax.setMaxWidth(70);
        columnaPax.setMinWidth(70);
        TableColumn columnaEfectivo = jTable_venta.getColumn("EFECTIVO");
        columnaEfectivo.setMaxWidth(70);
        columnaEfectivo.setMinWidth(70);        
        TableColumn columnaCredito = jTable_venta.getColumn("CREDITO");
        columnaCredito.setMaxWidth(70);
        columnaCredito.setMinWidth(70);
        TableColumn columnaTotal = jTable_venta.getColumn("TOTAL");
        columnaTotal.setMaxWidth(70);
        columnaTotal.setMinWidth(70);                

        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(model);
        jTable_venta.setRowSorter(elQueOrdena);

        Color myColorFondo = new Color(204,204,204);
        Color myColorFuente = new Color(0,0,115);
        jTable_venta.setBackground(myColorFondo);
        jTable_venta.setForeground(myColorFuente);
        

//        jTable_servicios.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int fila_point = jTable_servicios.rowAtPoint(e.getPoint());
////                int columna_point = 1;
//
//                if (fila_point > -1) {
//                    Id_servicioSeleccionado = (int) mode1.getValueAt(fila_point, 0);
//                    Servicios_update servicio_update = new Servicios_update();
//                    servicio_update.setVisible(true);
//
//                }
//            }
//
//        });
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
        jLabel_header = new javax.swing.JLabel();
        jButton_filtrar = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_venta = new javax.swing.JTable();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jButton_ReporteExcel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

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

        jLabel_footer.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 650, 150, 20));

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
        VaciarTabla();        
        Filtrar();        
    }//GEN-LAST:event_jButton_filtrarActionPerformed

    private void jButton_ReporteExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ReporteExcelActionPerformed
        // TODO add your handling code here:
        Date date = jDateChooser.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoTab = new SimpleDateFormat("d MMMM");
        String fecha = formato.format(date);
        String fechaTab = formatoTab.format(date);

        String encabezado_uno = "LIQUIDACION                        Fecha: " + fecha;
        String nombreTab_uno = fechaTab;
        String footer_uno = "LIQUIDADO POR: " + nombre + " " + apellido;
        
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

//                jTable_venta = new JTable(model);               

                try {
                    Util excel_exporter = new Util();
                    excel_exporter.exportToEXEL(jTable_venta, null, file, nombreTab_uno, "", encabezado_uno, "", footer_uno, "");
//                    System.out.println("Filas tabla en boton Excel: " + model.getRowCount());
//                    System.out.println("Columnas tabla en boton Excel: " + model.getColumnCount());
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
            java.util.logging.Logger.getLogger(Reporte_liquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporte_liquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporte_liquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporte_liquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reporte_liquidacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ReporteExcel;
    private javax.swing.JButton jButton_filtrar;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JLabel jLabel_header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_venta;
    // End of variables declaration//GEN-END:variables

    //método para vaciar tabla
    public void VaciarTabla() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }   
    
    public void Filtrar(){
        Date date = jDateChooser.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(date);
        
//        System.out.println("Fecha en método: " + fecha);
//        System.out.println("Tabla en método rowCount: " + jTable_venta.getRowCount());
//        System.out.println("Tabla en método columnCount: " + jTable_venta.getColumnCount());
//        System.out.println("Usuario en método: " + Login.user);
        
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select Descripcion, TE_alojamiento, Pax, Menores, Infantes, "
                    + "TE_traslado, Efectivo_hotel, Efectivo_trf, Tarjeta_credito, Credito_hotel, Credito_trf, Hotel, Destino "
                    + "from servicios where Reservado_por = '" + Login.user + "' and Reservado_fecha = '" + fecha + "'");

            ResultSet rs = pst.executeQuery();

            double importe_total_efectivo = 0, importe_total_credito = 0, importe_total_gral = 0;

            while (rs.next()) {
                Object[] fila_rs = new Object[8];
                Object[] fila_aloj = new Object[7];
                Object[] fila_trf = new Object[7];
                int adulto = 0, menor = 0, infante = 0;
                String hotel = "", destino = "";
                double importe_total_TEaloj = 0, importe_total_TEtrf = 0;

                for (int i = 0; i < 13; i++) {//llenando vector fila_rs con resultados del ResultSet
                    if (i >= 0 && i < 2) {//descripcion y TE alojamiento
                        fila_rs[i] = rs.getString(i + 1);
//                        System.out.print("i- " + i + " " + fila_rs[i] + "\n");
                    }
                    if (i == 2) {
                        String ad_string = rs.getString(i + 1);
                        if (!ad_string.equals("")) {
                            adulto += Integer.parseInt(ad_string);
//                            System.out.print("i- " + i + " " + adulto + "\n");
                        } else {
//                             System.out.print("i- " + i + "\n");
                        }

                    }
                    if (i == 3) {
                        String men_string = rs.getString(i + 1);
                        if (!men_string.equals("")) {
                            menor += Integer.parseInt(men_string);
//                            System.out.print("i- " + i + " " + menor + "\n");
                        } else {
//                            System.out.print("i- " + i + "\n");
                        }

                    }
                    if (i == 4) {
                        String inf_string = rs.getString(i + 1);
                        if (!inf_string.equals("")) {
                            infante += Integer.parseInt(inf_string);
//                            System.out.print("i- " + i + " " + infante + "\n");
                        } else {
//                            System.out.print("i- " + i + "\n");
                        }
                    }
                    if (i == 6) {//alojamiento efectivo      
                        fila_rs[i - 3] = rs.getDouble(i + 1);
                        importe_total_TEaloj += (double) fila_rs[i - 3];
//                        System.out.print("i- " + i + " " + fila_rs[i-3] + " acumulado- " + importe_total_TEaloj + "\n");
                        importe_total_efectivo += (double) fila_rs[i - 3];
                    }
                    if (i == 9) {//alojamiento crédito       
                        fila_rs[i - 3] = rs.getDouble(i + 1);
                        importe_total_TEaloj += (double) fila_rs[i - 3];
//                        System.out.print("i- " + i + " " + fila_rs[i-3] + " acumulado- " + importe_total_TEaloj + "\n");
                        importe_total_credito += (double) fila_rs[i - 3];
                    }
                    if (i == 7) {//trf efectivo      
                        fila_rs[i - 3] = rs.getDouble(i + 1);
                        importe_total_TEtrf += (double) fila_rs[i - 3];
//                        System.out.print("i- " + i + " " + fila_rs[i-3] + " acumulado- " + importe_total_TEtrf + "\n");
                        importe_total_efectivo += (double) fila_rs[i - 3];
                    }
                    if (i == 10) {//trf crédito        
                        fila_rs[i - 3] = rs.getDouble(i + 1);
                        importe_total_TEtrf += (double) fila_rs[i - 3];
//                        System.out.print("i- " + i + " " + fila_rs[i-3] + " acumulado- " + importe_total_TEtrf + "\n");
                        importe_total_credito += (double) fila_rs[i - 3];
                    }
                    if (i == 5 || i == 8) {//TE trf y tarjeta de crédito
                        fila_rs[i - 3] = rs.getString(i + 1);
//                        System.out.print("i- " + i + " " + fila_rs[i-3] + "\n");
                        if (i == 8) {
                            if (fila_rs[i - 3] == null) {
                                fila_rs[i - 3] = "";
                            }
                        }
                    }
                    if (i == 11) {
                        hotel = " " + rs.getString(i + 1);
                    }
                    if (i == 12) {
                        destino = " a " + rs.getString(i + 1);
                        if (destino.equals(" a Otros")) {
                            destino = "";
                        }
                    }
                }

//                for (int i = 0; i < fila_rs.length; i++) {
//                    System.out.print("(" + fila_rs[i] + ")");
//                }
//                System.out.println("");
//                System.out.println("----------------------------------------------------------------");
                
                if (!fila_rs[1].equals("")) {//llenando fila_aloj si TE_aloj diferente de ""
                    fila_aloj[0] = fila_rs[1];//TE alojamiento
                    fila_aloj[1] = fila_rs[0] + hotel;//Actividad
                    fila_aloj[2] = adulto + "+" + menor + "+" + infante;//Cant Pax
                    fila_aloj[3] = String.valueOf(fila_rs[3]).replace('.', ',');//Efectivo alojamiento                    
                    if ((double) fila_rs[6] == 0.0) {
                        fila_aloj[4] = "";//No Tarjeta de crédito
                    } else {
                        fila_aloj[4] = fila_rs[5];//No Tarjeta de crédito
                    }
                    fila_aloj[5] = String.valueOf(fila_rs[6]).replace('.', ',');//Crédito alojamiento
                    fila_aloj[6] = String.valueOf(importe_total_TEaloj).replace('.', ',');//Total (efectivo+crédito)
                    model.addRow(fila_aloj);
                    importe_total_gral += importe_total_TEaloj;
                }
                
                if (!fila_rs[2].equals("")) {//llenando fila_trf si TE_trf diferente de ""
                    fila_trf[0] = fila_rs[2];//TE TRF
                    fila_trf[1] = "TRF" + destino;//Actividad
                    fila_trf[2] = adulto + "+" + menor + "+" + infante;//Cant Pax
                    fila_trf[3] = String.valueOf(fila_rs[4]).replace('.', ',');//Efectivo TRF
                    if ((double) fila_rs[7] == 0.0) {
                        fila_trf[4] = "";//No Tarjeta de crédito
                    } else {
                        fila_trf[4] = fila_rs[5];//No Tarjeta de crédito
                    }
                    fila_trf[5] = String.valueOf(fila_rs[7]).replace('.', ',');//Crédito TRF
                    fila_trf[6] = String.valueOf(importe_total_TEtrf).replace('.', ',');////Total (efectivo+crédito)
                    model.addRow(fila_trf);
                    importe_total_gral += importe_total_TEtrf;
                }
            }
            Object[] fila = new Object[7];
            fila[0] = "TOTAL";
            fila[3] = String.valueOf(importe_total_efectivo).replace('.', ',');
            fila[5] = String.valueOf(importe_total_credito).replace('.', ',');
            fila[6] = String.valueOf(importe_total_gral).replace('.', ',');
            model.addRow(fila);
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error al llenar tabla. " + e);
            JOptionPane.showMessageDialog(null, "!!Error al mostrar tabla...contacte al administrador");
        }
    }  
}
