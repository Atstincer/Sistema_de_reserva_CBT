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
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import jxl.write.WriteException;

/**
 *
 * @author ALEX
 */
public class Reporte_paraTransportista extends javax.swing.JFrame {

    DefaultTableModel model_ida = new DefaultTableModel();
    DefaultTableModel model_regreso = new DefaultTableModel();
    

    /**
     * Creates new form Clientes_AdminII
     */
    public Reporte_paraTransportista() {
        initComponents();
        setSize(1000, 750);
        setResizable(false);
        setTitle("Servicios con transporte - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jDateChooser1.setDate(null);

//        Conformar_model(model_ida, jTable_serviciosIda, jScrollPane_ida);
//        Conformar_model(model_regreso, jTable_serviciosRegreso, jScrollPane_regreso);

        Conformar_model(model_ida);
        Conformar_model(model_regreso);        

        jTable_serviciosIda = new JTable(model_ida);
        jScrollPane_ida.setViewportView(jTable_serviciosIda);        
        jTable_serviciosRegreso = new JTable(model_regreso);
        jScrollPane_regreso.setViewportView(jTable_serviciosRegreso);        

        FijarAnchoColumnas(jTable_serviciosIda);
        FijarAnchoColumnas(jTable_serviciosRegreso);
        
        Color myColorFondo = new Color(204,204,204);
        Color myColorFuente = new Color(0,0,115);
        jTable_serviciosIda.setBackground(myColorFondo);
        jTable_serviciosIda.setForeground(myColorFuente);
        jTable_serviciosRegreso.setBackground(myColorFondo);
        jTable_serviciosRegreso.setForeground(myColorFuente);       
            
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setBackground(myColorFondo);
          
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
        jButton_filtrar = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jScrollPane_ida = new javax.swing.JScrollPane();
        jTable_serviciosIda = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane_regreso = new javax.swing.JScrollPane();
        jTable_serviciosRegreso = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBox_destino = new javax.swing.JComboBox<>();
        jButton_Excel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("SERVICIOS CON TRANSPORTE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 25, -1, -1));

        jButton_filtrar.setBackground(new java.awt.Color(153, 153, 153));
        jButton_filtrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_filtrar.setText("Filtrar");
        jButton_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_filtrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_filtrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 660, 80, 30));

        jLabel_footer.setText("?? Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 700, 150, 20));

        jTable_serviciosIda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable_serviciosIda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_serviciosIda.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_serviciosIda.setCellSelectionEnabled(true);
        jTable_serviciosIda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane_ida.setViewportView(jTable_serviciosIda);

        jPanel1.add(jScrollPane_ida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 970, 250));

        jLabel3.setText("IDA:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jTable_serviciosRegreso.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable_serviciosRegreso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_serviciosRegreso.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_serviciosRegreso.setCellSelectionEnabled(true);
        jTable_serviciosRegreso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane_regreso.setViewportView(jTable_serviciosRegreso);

        jPanel1.add(jScrollPane_regreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 970, 250));

        jLabel4.setText("RETORNO:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 375, -1, -1));

        jDateChooser1.setBackground(new java.awt.Color(153, 153, 153));
        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser1KeyPressed(evt);
            }
        });
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 130, 25));

        jComboBox_destino.setBackground(new java.awt.Color(100, 100, 100));
        jComboBox_destino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Varadero", "CSM", "CayoCoco", "Cienfuegos", "Trinidad" }));
        jPanel1.add(jComboBox_destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 40, 100, 25));

        jButton_Excel.setBackground(new java.awt.Color(153, 153, 153));
        jButton_Excel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_Excel.setText("Excel");
        jButton_Excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExcelActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_Excel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 660, 80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_filtrarActionPerformed
        // TODO add your handling code here:
        Filtrar();
    }//GEN-LAST:event_jButton_filtrarActionPerformed

    private void jButton_ExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExcelActionPerformed

        Date date = jDateChooser1.getDate();
        Locale espanol = new Locale("es");
        SimpleDateFormat formato = new SimpleDateFormat("EEEE d MMMM yyyy", espanol);
        String fecha = formato.format(date);

        String encabezadoTablaIda = "Reservas con TRASLADO IDA: " + fecha;
        String encabezadoTablaRegreso = "Reservas con TRASLADO REGRESO: " + fecha;
        String ida_origen = "", ida_hora = "", regreso_origen = "", regreso_hora = "";
//        System.out.println(encabezadoTablaIda);

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

            cn.close();

        } catch (SQLException e) {
            System.err.println("Error al conectar a la BD: " + e);
        }

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

                jTable_serviciosIda = new JTable(model_ida);
                jTable_serviciosRegreso = new JTable(model_regreso);

                try {
                    Util excel_exporter = new Util();
                    excel_exporter.exportToEXEL(jTable_serviciosIda, jTable_serviciosRegreso, file, "Ida", "Regreso",
                            encabezadoTablaIda, encabezadoTablaRegreso, "Salida " + ida_hora + " desde " + ida_origen, ""
                            + "Regreso " + regreso_hora + " desde " + regreso_origen);
//                    System.out.println("Filas tabla en PDF: " + model_ida.getRowCount());
//                    System.out.println("Columnas tabla en PDF: " + model_ida.getColumnCount());
                } catch (WriteException ex) {
//                        Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Error: " + ex);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo crear el archivo " + file.getAbsolutePath());
            // Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_ExcelActionPerformed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jDateChooser1KeyPressed

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
            java.util.logging.Logger.getLogger(Reporte_paraTransportista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporte_paraTransportista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporte_paraTransportista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporte_paraTransportista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Reporte_paraTransportista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Excel;
    private javax.swing.JButton jButton_filtrar;
    private javax.swing.JComboBox<String> jComboBox_destino;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane_ida;
    private javax.swing.JScrollPane jScrollPane_regreso;
    private javax.swing.JTable jTable_serviciosIda;
    private javax.swing.JTable jTable_serviciosRegreso;
    // End of variables declaration//GEN-END:variables

    //metodo para conformar DefaultTableMode
    public void Conformar_model(DefaultTableModel model) {
        model.addColumn("Hotel");
        model.addColumn("Cliente");
        model.addColumn("Ad");
        model.addColumn("Men");
        model.addColumn("Inf");
        model.addColumn("Desde");
        model.addColumn("Hasta");
        model.addColumn("TE hotel");
        model.addColumn("Conf.");
        model.addColumn("TE TRF");
        model.addColumn("Observaciones");
    }

    //m??todo para vaciar tablas
    public void VaciarModel(DefaultTableModel model) {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    //m??todo para fijar ancho columnas
    public void FijarAnchoColumnas(JTable tabla) {
        TableColumn columnaAd = tabla.getColumn("Ad");
        columnaAd.setMinWidth(35);
        columnaAd.setMaxWidth(35);        
        TableColumn columnaMen = tabla.getColumn("Men");
        columnaMen.setMinWidth(35);
        columnaMen.setMaxWidth(35);
        TableColumn columnaInf = tabla.getColumn("Inf");
        columnaInf.setMinWidth(30);
        columnaInf.setMaxWidth(30);
        TableColumn columnaDesde = tabla.getColumn("Desde");
        columnaDesde.setMinWidth(80);
        columnaDesde.setMaxWidth(80);
        TableColumn columnaHasta = tabla.getColumn("Hasta");
        columnaHasta.setMinWidth(80);
        columnaHasta.setMaxWidth(80);
        TableColumn columnaTEhotel = tabla.getColumn("TE hotel");
        columnaTEhotel.setMinWidth(80);
        columnaTEhotel.setMaxWidth(80);
        TableColumn columnaTEtrf = tabla.getColumn("TE TRF");
        columnaTEtrf.setMinWidth(80);
        columnaTEtrf.setMaxWidth(80);
        TableColumn columnaConf = tabla.getColumn("Conf.");
        columnaConf.setMinWidth(80);
        columnaConf.setMaxWidth(80);
    }
    
    //para filtrar seg??n fecha
    public void Filtrar(){
         if (jDateChooser1.getDate() != null) {

            VaciarModel(model_ida);
            VaciarModel(model_regreso);     

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date date = jDateChooser1.getDate();
            String fecha = formato.format(date);
            String destino = jComboBox_destino.getSelectedItem().toString();
            int cant_Id_cliente = 0, posicion = 0, ad = 0, men = 0, inf = 0;
            String apellido = "";           

            try {
                Connection cn = Conexion.conectar();

                PreparedStatement pst = cn.prepareStatement("select Id_cliente from servicios where Fecha_inicio = '"
                        + "" + fecha + "' and (Traslado = 'Si' or Traslado = 'Solo_ida' or Traslado = 'Solo_traslado') "
                        + "and CXX != 'SI' and CXX != 'SI_trf' and Destino = '" + destino + "'");

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {//estableciendo cant de registros hayados
                    cant_Id_cliente++;
                }

                String[] vector_id_cliente_ida = new String[cant_Id_cliente];
                String[] vector_celular_cliente_ida = new String[cant_Id_cliente];

                if (cant_Id_cliente != 0) {//si hay resultados en la consulta

                    rs = pst.executeQuery();

                    while (rs.next()) {//llenando vector Id_cliente
                        vector_id_cliente_ida[posicion] = rs.getString("Id_cliente");
                        posicion++;
                    }

                    posicion = 0;

                    for (int i = 0; i < cant_Id_cliente; i++) {//llenando vector_celular_cliente
                        if (!vector_id_cliente_ida[i].equals("")) {
                            pst = cn.prepareStatement("select Telefono_cell from clientes where "
                                    + "Id_cliente = '" + vector_id_cliente_ida[i] + "'");

                            rs = pst.executeQuery();

                            if (rs.next()) {
                                vector_celular_cliente_ida[i] = "-" + rs.getString(1);
                                if (vector_celular_cliente_ida[i].equals("-")) {
                                    vector_celular_cliente_ida[i] = "";
                                }
                            } else {
                                vector_celular_cliente_ida[i] = "";
                            }
                        } else {
                            vector_celular_cliente_ida[i] = "";
                        }
                    }

//                    System.out.println("Vector Id cliente IDA");
//                    for (int i = 0; i < vector_id_cliente_ida.length; i++) {
//                        System.out.print("(" + vector_id_cliente_ida[i] + ")");
//                    }
//                    System.out.println("");
//                    System.out.println("");
//                    
//                    System.out.println("Vector celular cliente IDA:");
//                    for (int i = 0; i < vector_celular_cliente_ida.length; i++) {
//                        System.out.print("(" + vector_celular_cliente_ida[i] + ")");
//                    }
//                    System.out.println("");
                    //llenando tabla
                    pst = cn.prepareStatement("select Hotel, Nombre_cliente, Pax, Menores, Infantes, "
                            + "Fecha_inicio, Fecha_fin, TE_alojamiento, No_conf, TE_traslado, Observaciones, "
                            + "Apellido_cliente, CXX from servicios where Fecha_inicio = '" + fecha + "' and "
                            + "(Traslado = 'Si' or Traslado = 'Solo_ida' or Traslado = 'Solo_traslado') and "
                            + "Destino = '" + destino + "' and CXX != 'SI' and CXX != 'SI_trf'");

                    rs = pst.executeQuery();

                    Object fila[] = new Object[11];
                    String cxx = "";

                    while (rs.next()) {                       
                        for (int i = 0; i < 13; i++) {
                            if (i == 2) {
                                fila[i] = rs.getString(i + 1);
                                if (!fila[i].toString().equals("")) {
                                    ad = ad + Integer.parseInt(fila[i].toString());
                                }
                            } else if (i == 3) {
                                fila[i] = rs.getString(i + 1);
                                if (!fila[i].toString().equals("")) {
                                    men = men + Integer.parseInt(fila[i].toString());
                                }
                            } else if (i == 4) {
                                fila[i] = rs.getString(i + 1);
                                if (!fila[i].toString().equals("")) {
                                    inf = inf + Integer.parseInt(fila[i].toString());
                                }
                            } else if (i == 11) {
                                apellido = rs.getString(i + 1);
                                if (apellido != null) {
                                    fila[1] = fila[1] + " " + apellido;
                                }
                            } else if (i == 12){
                                cxx = rs.getString(i + 1);
                            } else {
                                fila[i] = rs.getString(i + 1);
                            }
                        }

                        if (fila[10] != null) {
                            fila[10] = fila[10] + "\n" + vector_celular_cliente_ida[posicion];
                        } else {
                            fila[10] = vector_celular_cliente_ida[posicion];
                        }
                        
                        if(cxx.equals("SI_aloj")){
                            fila[7] = "CXX";
                        }

                        model_ida.addRow(fila);
                        posicion++;
                    }
                    
                    for (int i = 0; i < fila.length; i++) {
                        fila[i] = "";
                    }

                    model_ida.addRow(fila);

                    for (int i = 0; i < fila.length; i++) {
                        if (i == 0) {
                            fila[i] = "TOTAL";
                        } else if (i == 2) {
                            fila[i] = ad;
                        } else if (i == 3) {
                            fila[i] = men;
                        } else if (i == 4) {
                            fila[i] = inf;
                        } else {
                            fila[i] = "";
                        }
                    }
                    model_ida.addRow(fila);

                    posicion = 0;

                }else{
                    JOptionPane.showMessageDialog(null, "No se encontr?? ning??n servicio con TRF IDA el " + fecha);
                }

                cant_Id_cliente = 0;

                //para tabla regreso
                pst = cn.prepareStatement("select Id_cliente from servicios where Fecha_fin = '" + fecha + "' and "
                        + "(Traslado = 'Si' or Traslado = 'Solo_regreso' or Traslado = 'Solo_traslado') and "
                        + "Destino = '" + destino + "' and CXX != 'SI' and CXX != 'SI_trf'");

                rs = pst.executeQuery();

                while (rs.next()) {//estableciendo cant de registros hayados
                    cant_Id_cliente++;
                }

                String[] vector_id_cliente_regreso = new String[cant_Id_cliente];
                String[] vector_celular_cliente_regreso = new String[cant_Id_cliente];

                if (cant_Id_cliente != 0) {//si hay resultados en la consulta

                    rs = pst.executeQuery();

                    while (rs.next()) {//llenando vector Id_cliente
                        vector_id_cliente_regreso[posicion] = rs.getString("Id_cliente");
                        posicion++;
                    }

                    posicion = 0;

                    for (int i = 0; i < cant_Id_cliente; i++) {//llenando vector_celular_cliente
                        if (!vector_id_cliente_regreso[i].equals("")) {
                            pst = cn.prepareStatement("select Telefono_cell from clientes where "
                                    + "Id_cliente = '" + vector_id_cliente_regreso[i] + "'");

                            rs = pst.executeQuery();

                            if (rs.next()) {
                                vector_celular_cliente_regreso[i] = "-" + rs.getString(1);
                                if (vector_celular_cliente_regreso[i].equals("-")) {
                                    vector_celular_cliente_regreso[i] = "";
                                }
                            } else {
                                vector_celular_cliente_regreso[i] = "";
                            }
                        } else {
                            vector_celular_cliente_regreso[i] = "";
                        }
                    }

//                    System.out.println("");
//                    System.out.println("Vector Id cliente Regreso");
//                    for (int i = 0; i < vector_id_cliente_regreso.length; i++) {
//                        System.out.print("(" + vector_id_cliente_regreso[i] + ")");
//                    }
//                    System.out.println("");
//                    System.out.println("");
//                    
//                    System.out.println("Vector celular cliente Regreso:");
//                    for (int i = 0; i < vector_celular_cliente_regreso.length; i++) {
//                        System.out.print("(" + vector_celular_cliente_regreso[i] + ")");
//                    }
//                    System.out.println("");
                    //llenando tabla Regreso
                    pst = cn.prepareStatement("select Hotel, Nombre_cliente, Pax, Menores, Infantes, "
                            + "Fecha_inicio, Fecha_fin, TE_alojamiento, No_conf, TE_traslado, Observaciones, "
                            + "Apellido_cliente, CXX from servicios where Fecha_fin = '" + fecha + "' and "
                            + "(Traslado = 'Si' or Traslado = 'Solo_regreso' or Traslado = 'Solo_traslado') and "
                            + "Destino = '" + destino + "' and CXX != 'SI' and CXX != 'SI_trf'");

                    rs = pst.executeQuery();

                    Object fila[] = new Object[11];
                    String cxx = "";

                    ad = 0;
                    men = 0;
                    inf = 0;

                    while (rs.next()) {                        
                        for (int i = 0; i < 13; i++) {
                            if (i == 2) {
                                fila[i] = rs.getString(i + 1);
                                if (!fila[i].toString().equals("")) {
                                    ad = ad + Integer.parseInt(fila[i].toString());
                                }
                            } else if (i == 3) {
                                fila[i] = rs.getString(i + 1);
                                if (!fila[i].toString().equals("")) {
                                    men = men + Integer.parseInt(fila[i].toString());
                                }
                            } else if (i == 4) {
                                fila[i] = rs.getString(i + 1);
                                if (!fila[i].toString().equals("")) {
                                    inf = inf + Integer.parseInt(fila[i].toString());
                                }
                            } else if (i == 11) {
                                apellido = rs.getString(i + 1);
                                if (apellido != null) {
                                    fila[1] = fila[1] + " " + apellido;
                                }
                            } else if(i == 12) {
                                cxx = rs.getString(i + 1);
                            }else{
                                fila[i] = rs.getString(i + 1);
                            }
                        }

                        if (fila[10] != null) {
                            fila[10] = fila[10] + "\n" + vector_celular_cliente_regreso[posicion];
                        } else {
                            fila[10] = vector_celular_cliente_regreso[posicion];
                        }
                        
                        if(cxx.equals("SI_aloj")){
                            fila[7] = "CXX";
                        }

                        model_regreso.addRow(fila);
                        posicion++;
                    }                    

                    for (int i = 0; i < fila.length; i++) {
                        fila[i] = "";
                    }

                    model_regreso.addRow(fila);

                    for (int i = 0; i < fila.length; i++) {
                        if (i == 0) {
                            fila[i] = "TOTAL";
                        } else if (i == 2) {
                            fila[i] = String.valueOf(ad);
                        } else if (i == 3) {
                            fila[i] = String.valueOf(men);
                        } else if (i == 4) {
                            fila[i] = String.valueOf(inf);
                        } else {
                            fila[i] = "";
                        }
                    }
                    model_regreso.addRow(fila);
                    posicion = 0;

                }else{
                    JOptionPane.showMessageDialog(null, "No se encontr?? ning??n servicio con TRF REGRESO el " + fecha);
                }

                cn.close();

            } catch (SQLException e) {
                System.err.println("Error al comunicar con BD: " + e);
                JOptionPane.showMessageDialog(null, "!!Error al acceder a la BD. Contacte al administrador.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha.");
        }
    }
}
