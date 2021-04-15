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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import jxl.write.WriteException;

/**
 *
 * @author ALEX
 */
public class Hotel_sin_confirmar extends javax.swing.JFrame {

    private int Id_servicioSeleccionado;
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form Clientes_AdminII
     */
    public Hotel_sin_confirmar() {
        initComponents();
        setSize(1000, 500);
        setResizable(false);
        setTitle("Pendiente confirmación - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jDialog_Servicio_actualizar.setSize(740, 625);
        jDialog_Servicio_actualizar.setResizable(false);
        jDialog_Servicio_actualizar.setTitle("Actualizar servicio - " + Login.user);
        jDialog_Servicio_actualizar.setLocationRelativeTo(null);
        jDialog_Servicio_actualizar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jDialog_Servicio_actualizar.setModal(true);

        jTextField_days_ahead.setText("7");
        jCheckBox_incluir_noHotel.setSelected(true);

        jTable_servicios_no_confirmados = new JTable(model);
        jScrollPane1.setViewportView(jTable_servicios_no_confirmados);

        model.addColumn("Id");
        model.addColumn("Descripción");
        model.addColumn("TE Hotel");
        model.addColumn("Cliente");
        model.addColumn("Pax");
        model.addColumn("Hotel");
        model.addColumn("Conf.");
        model.addColumn("Desde");
        model.addColumn("Hasta");
        model.addColumn("TRF");
        model.addColumn("Obs.");

        FijarAnchoColumnas();

        Color myColorFondo = new Color(204, 204, 204);
        Color myColorFuente = new Color(0, 0, 115);
        jTable_servicios_no_confirmados.setBackground(myColorFondo);
        jTable_servicios_no_confirmados.setForeground(myColorFuente);

        filtrar();

        jTable_servicios_no_confirmados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = jTable_servicios_no_confirmados.rowAtPoint(e.getPoint());
//                int columna_point = 1;

                if (fila_point > -1) {
                    Id_servicioSeleccionado = (int) model.getValueAt(fila_point, 0);

                    String Id_cliente = "", desde = "", hasta = "", nombre = "", apellido = "";

                    try {//descargando datos del servicio
                        Connection cn = Conexion.conectar();
                        PreparedStatement pst = cn.prepareStatement("select Id_cliente, Descripcion, TE_alojamiento, Hotel, Destino, "
                                + "Fecha_inicio, Fecha_fin, Traslado, TE_traslado, Reservado_fecha, Reservado_por, Observaciones, "
                                + "Proveedor_trf, No_conf, Pax, Nombre_cliente, Menores, Infantes, Apellido_cliente, Efectivo_hotel, "
                                + "Efectivo_trf, Credito_hotel, Credito_trf, Tarjeta_credito from servicios where Id_servicio = '"
                                + "" + Id_servicioSeleccionado + "'");

                        ResultSet rs = pst.executeQuery();

                        if (rs.next()) {
                            Id_cliente = rs.getString("Id_cliente");
                            jTextField_descripcionServ.setText(rs.getString("Descripcion"));
                            jTextField_TEaloj.setText(rs.getString("TE_alojamiento"));
                            jTextField_hotel.setText(rs.getString("Hotel"));
                            jComboBox_destino.setSelectedItem(rs.getString("Destino"));
                            desde = rs.getString("Fecha_inicio");
                            hasta = rs.getString("Fecha_fin");
                            jComboBox_trf.setSelectedItem(rs.getString("Traslado"));
                            jTextField_TEtrf.setText(rs.getString("TE_traslado"));
                            jTextField_reservadoFecha.setText(rs.getString("Reservado_fecha"));
                            jTextField_reservadoPor.setText(rs.getString("Reservado_por"));
                            jTextArea1_observacionesServicio.setText(rs.getString("Observaciones"));
                            jComboBox_proveedorTRF.setSelectedItem(rs.getString("Proveedor_trf"));
                            jTextField_confirmacion.setText(rs.getString("No_conf"));
                            jTextField_pax.setText(rs.getString("Pax"));
                            nombre = rs.getString("Nombre_cliente");
                            jTextField_menor.setText(rs.getString("Menores"));
                            jTextField_infante.setText(rs.getString("Infantes"));
                            apellido = rs.getString("Apellido_cliente");
                            jTextField_aloj_efectivo.setText(String.valueOf(rs.getDouble("Efectivo_hotel")));
                            jTextField_trf_efectivo.setText(String.valueOf(rs.getDouble("Efectivo_trf")));
                            jTextField_aloj_credito.setText(String.valueOf(rs.getDouble("Credito_hotel")));
                            jTextField_trf_credito.setText(String.valueOf(rs.getDouble("Credito_trf")));
                            jTextArea1_tarjetas_credito.setText(rs.getString("Tarjeta_credito"));
                        }
                        cn.close();
                    } catch (SQLException ev) {
                        System.err.println("Error al intentar obtener datos del servicio: " + ev);
                        JOptionPane.showMessageDialog(null, "Error al conectarse con la BD. Contacte al administrador.");
                    }

                    try {//colocando fechas en jdatechooser
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecha_desde = formato.parse(desde);
                        Date fecha_hasta = formato.parse(hasta);
                        jDateChooser_fechaIda.setDate(fecha_desde);
                        jDateChooser_fechaRegreso.setDate(fecha_hasta);
                    } catch (ParseException ev) {
                        System.err.println("Error parseando fecha: " + ev);
                    }

                    if (!Id_cliente.equals("")) {//llenando datos cliente vinculado al servicio
                        try {
                            Connection cn1 = Conexion.conectar();
                            PreparedStatement pst1 = cn1.prepareStatement("select Nombre, Apellido, Nacionalidad, Telefono_fijo, "
                                    + "Telefono_cell, E_mail, Observaciones from clientes where Id_cliente = '" + Id_cliente + "'");

                            ResultSet rs1 = pst1.executeQuery();

                            if (rs1.next()) {
                                jTextField_nombreCliente.setText(rs1.getString("Nombre"));
                                jTextField_apellidoCliente.setText(rs1.getString("Apellido"));
                                jTextField_nacionalidadCliente.setText(rs1.getString("Nacionalidad"));
                                jTextField_telefonoCliente.setText(rs1.getString("Telefono_fijo"));
                                jTextField_celularCliente.setText(rs1.getString("Telefono_cell"));
                                jTextField_EmailCliente.setText(rs1.getString("E_mail"));
                                jTextArea1_observacionesCliente.setText(rs1.getString("Observaciones"));

                                cn1.close();
                            } else {
                                jTextField_nombreCliente.setText(nombre);
                                jTextField_apellidoCliente.setText(apellido);
                            }
                        } catch (SQLException ev) {
                            System.err.println("Error accediendo tabla clientes en constructor: " + ev);
                            JOptionPane.showMessageDialog(null, "!!Error conectando con BD. Contacte al administrador.");
                        }
                    } else {
                        jTextField_nombreCliente.setText(nombre);
                        jTextField_apellidoCliente.setText(apellido);
                    }
                    
                    jDialog_Servicio_actualizar.setVisible(true);                    
                }
            }

        });
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

        jDialog_Servicio_actualizar = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_pax = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_descripcionServ = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField_hotel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField_TEaloj = new javax.swing.JTextField();
        jComboBox_destino = new javax.swing.JComboBox<>();
        jComboBox_trf = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_TEtrf = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox_proveedorTRF = new javax.swing.JComboBox<>();
        jButton_actualizarServ = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField_nombreCliente = new javax.swing.JTextField();
        jTextField_apellidoCliente = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField_nacionalidadCliente = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField_telefonoCliente = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField_celularCliente = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField_EmailCliente = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1_observacionesCliente = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jDateChooser_fechaIda = new com.toedter.calendar.JDateChooser();
        jDateChooser_fechaRegreso = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1_observacionesServicio = new javax.swing.JTextArea();
        jLabel_footer1 = new javax.swing.JLabel();
        jTextField_confirmacion = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField_reservadoFecha = new javax.swing.JTextField();
        jTextField_reservadoPor = new javax.swing.JTextField();
        jButton_historialModificaciones = new javax.swing.JButton();
        jTextField_menor = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField_infante = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField_aloj_efectivo = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField_aloj_credito = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1_tarjetas_credito = new javax.swing.JTextArea();
        jTextField_trf_efectivo = new javax.swing.JTextField();
        jTextField_trf_credito = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_days_ahead = new javax.swing.JTextField();
        jButton_filtrar = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_servicios_no_confirmados = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton_excel = new javax.swing.JButton();
        jCheckBox_incluir_noHotel = new javax.swing.JCheckBox();

        jDialog_Servicio_actualizar.setIconImage(getIconImage());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("INFORMACION GENERAL");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 30, -1, -1));

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Adu");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        jTextField_pax.setEditable(false);
        jTextField_pax.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_pax.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_pax.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_pax, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 105, 30, 30));

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Descripción");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jTextField_descripcionServ.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_descripcionServ.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_descripcionServ.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_descripcionServ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 150, 30));

        jLabel8.setText("Hotel");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 90, -1, -1));

        jTextField_hotel.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_hotel.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_hotel.setAutoscrolls(false);
        jTextField_hotel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_hotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 105, 150, 30));

        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("TE alojamiento");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 90, -1, -1));

        jTextField_TEaloj.setEditable(false);
        jTextField_TEaloj.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_TEaloj.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_TEaloj.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_TEaloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 105, 100, 30));

        jComboBox_destino.setBackground(new java.awt.Color(100, 100, 100));
        jComboBox_destino.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_destino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Otros", "Varadero", "CSM", "CayoCoco", "Cienfuegos", "Trinidad" }));
        jComboBox_destino.setEnabled(false);
        jPanel2.add(jComboBox_destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 100, 30));

        jComboBox_trf.setBackground(new java.awt.Color(100, 100, 100));
        jComboBox_trf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_trf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Solo_ida", "Solo_regreso", "Solo_traslado" }));
        jComboBox_trf.setEnabled(false);
        jPanel2.add(jComboBox_trf, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 100, 30));

        jLabel10.setText("Destino");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 155, -1, -1));

        jLabel11.setText("Traslado");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 155, -1, -1));

        jLabel12.setText("TE traslado");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 155, -1, -1));

        jTextField_TEtrf.setEditable(false);
        jTextField_TEtrf.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_TEtrf.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_TEtrf.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_TEtrf, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 100, 30));

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Desde");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 155, -1, -1));

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Hasta");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 155, -1, -1));

        jLabel15.setText("Proveedor");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 155, -1, -1));

        jComboBox_proveedorTRF.setBackground(new java.awt.Color(100, 100, 100));
        jComboBox_proveedorTRF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Transtur", "Transgvt", "Otro" }));
        jComboBox_proveedorTRF.setEnabled(false);
        jPanel2.add(jComboBox_proveedorTRF, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 90, 30));

        jButton_actualizarServ.setBackground(new java.awt.Color(153, 153, 153));
        jButton_actualizarServ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_actualizarServ.setText("Actualizar");
        jButton_actualizarServ.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_actualizarServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarServActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_actualizarServ, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 525, 100, 40));

        jLabel16.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 640, -1));

        jLabel17.setText("Observaciones");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("CLIENTE RELACIONADO");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 360, -1, -1));

        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("Nombre");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        jTextField_nombreCliente.setEditable(false);
        jTextField_nombreCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_nombreCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_nombreCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_nombreCliente.setFocusTraversalPolicyProvider(true);
        jPanel2.add(jTextField_nombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 405, 100, 30));

        jTextField_apellidoCliente.setEditable(false);
        jTextField_apellidoCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_apellidoCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_apellidoCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_apellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 405, 100, 30));

        jLabel20.setText("Apellido");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, -1, -1));

        jLabel21.setText("Nacionalidad");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 455, -1, -1));

        jTextField_nacionalidadCliente.setEditable(false);
        jTextField_nacionalidadCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_nacionalidadCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_nacionalidadCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_nacionalidadCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, 80, 30));

        jLabel22.setText("(opcional)");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 362, -1, -1));

        jTextField_telefonoCliente.setEditable(false);
        jTextField_telefonoCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_telefonoCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_telefonoCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_telefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 405, 80, 30));

        jLabel23.setText("Teléfono");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, -1, -1));

        jTextField_celularCliente.setEditable(false);
        jTextField_celularCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_celularCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_celularCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_celularCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 405, 80, 30));

        jLabel24.setText("Celular");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, -1, -1));

        jTextField_EmailCliente.setEditable(false);
        jTextField_EmailCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_EmailCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_EmailCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_EmailCliente.setFocusTraversalPolicyProvider(true);
        jPanel2.add(jTextField_EmailCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 210, 30));

        jLabel25.setText("E-mail");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 455, -1, -1));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1_observacionesCliente.setEditable(false);
        jTextArea1_observacionesCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1_observacionesCliente.setColumns(1);
        jTextArea1_observacionesCliente.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1_observacionesCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextArea1_observacionesCliente.setRows(5);
        jScrollPane2.setViewportView(jTextArea1_observacionesCliente);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 405, 180, 100));

        jLabel26.setText("Observaciones");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, -1, -1));

        jDateChooser_fechaIda.setBackground(new java.awt.Color(153, 153, 153));
        jDateChooser_fechaIda.setDateFormatString("dd/MM/yyyy");
        jDateChooser_fechaIda.setEnabled(false);
        jPanel2.add(jDateChooser_fechaIda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 130, 30));

        jDateChooser_fechaRegreso.setBackground(new java.awt.Color(153, 153, 153));
        jDateChooser_fechaRegreso.setDateFormatString("dd/MM/yyyy");
        jDateChooser_fechaRegreso.setEnabled(false);
        jPanel2.add(jDateChooser_fechaRegreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 130, 30));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1_observacionesServicio.setEditable(false);
        jTextArea1_observacionesServicio.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1_observacionesServicio.setColumns(1);
        jTextArea1_observacionesServicio.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1_observacionesServicio.setForeground(new java.awt.Color(0, 0, 115));
        jTextArea1_observacionesServicio.setRows(5);
        jScrollPane3.setViewportView(jTextArea1_observacionesServicio);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 235, 180, 100));

        jLabel_footer1.setText("© Realizado por atstincer");
        jPanel2.add(jLabel_footer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 575, 150, 20));

        jTextField_confirmacion.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_confirmacion.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_confirmacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_confirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 105, 80, 30));

        jLabel27.setText("Confirmación");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, -1, -1));

        jLabel28.setText("Reservado:");
        jLabel28.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 70, -1));

        jTextField_reservadoFecha.setEditable(false);
        jTextField_reservadoFecha.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_reservadoFecha.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_reservadoFecha.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_reservadoFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 238, 80, 30));

        jTextField_reservadoPor.setEditable(false);
        jTextField_reservadoPor.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_reservadoPor.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_reservadoPor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_reservadoPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_reservadoPorActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField_reservadoPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 278, 80, 30));

        jButton_historialModificaciones.setBackground(new java.awt.Color(153, 153, 153));
        jButton_historialModificaciones.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_historialModificaciones.setText("Historial modificaciones");
        jButton_historialModificaciones.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_historialModificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_historialModificacionesActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_historialModificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, 150, 25));

        jTextField_menor.setEditable(false);
        jTextField_menor.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_menor.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_menor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_menor, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 105, 30, 30));

        jLabel29.setText("Niños");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, -1, -1));

        jTextField_infante.setEditable(false);
        jTextField_infante.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_infante.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_infante.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_infante, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 105, 30, 30));

        jLabel30.setText("Infante");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, -1, -1));

        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setText("- campos obligatorios");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

        jLabel32.setText("Alojamiento");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jTextField_aloj_efectivo.setEditable(false);
        jTextField_aloj_efectivo.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_aloj_efectivo.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_aloj_efectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_aloj_efectivo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_aloj_efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_aloj_efectivoActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField_aloj_efectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 245, 80, 25));

        jLabel33.setText("Efectivo");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        jTextField_aloj_credito.setEditable(false);
        jTextField_aloj_credito.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_aloj_credito.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_aloj_credito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_aloj_credito.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_aloj_credito, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 245, 80, 25));

        jLabel34.setText("Crédito");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        jLabel35.setText("Tarjetas de crédito");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, -1));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1_tarjetas_credito.setEditable(false);
        jTextArea1_tarjetas_credito.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1_tarjetas_credito.setColumns(1);
        jTextArea1_tarjetas_credito.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1_tarjetas_credito.setForeground(new java.awt.Color(0, 0, 115));
        jTextArea1_tarjetas_credito.setRows(5);
        jScrollPane4.setViewportView(jTextArea1_tarjetas_credito);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 245, 150, 60));

        jTextField_trf_efectivo.setEditable(false);
        jTextField_trf_efectivo.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_trf_efectivo.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_trf_efectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_trf_efectivo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_trf_efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_trf_efectivoActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField_trf_efectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 80, 25));

        jTextField_trf_credito.setEditable(false);
        jTextField_trf_credito.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_trf_credito.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_trf_credito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_trf_credito.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel2.add(jTextField_trf_credito, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 80, 25));

        jLabel36.setText("Traslado");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 285, -1, -1));

        javax.swing.GroupLayout jDialog_Servicio_actualizarLayout = new javax.swing.GroupLayout(jDialog_Servicio_actualizar.getContentPane());
        jDialog_Servicio_actualizar.getContentPane().setLayout(jDialog_Servicio_actualizarLayout);
        jDialog_Servicio_actualizarLayout.setHorizontalGroup(
            jDialog_Servicio_actualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_Servicio_actualizarLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog_Servicio_actualizarLayout.setVerticalGroup(
            jDialog_Servicio_actualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_Servicio_actualizarLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("PENDIENTE CONFIRMACION");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 43, -1, -1));

        jTextField_days_ahead.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_days_ahead.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_days_ahead.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_days_ahead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_days_aheadActionPerformed(evt);
            }
        });
        jTextField_days_ahead.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_days_aheadKeyPressed(evt);
            }
        });
        jPanel1.add(jTextField_days_ahead, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 40, 30, 30));

        jButton_filtrar.setBackground(new java.awt.Color(153, 153, 153));
        jButton_filtrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_filtrar.setText("Filtrar");
        jButton_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_filtrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_filtrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 415, 80, 30));

        jLabel_footer.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 150, 20));

        jLabel2.setText("- Click sobre la tabla para seleccionar servicio.");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 397, -1, -1));

        jTable_servicios_no_confirmados.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable_servicios_no_confirmados.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_servicios_no_confirmados.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_servicios_no_confirmados.setCellSelectionEnabled(true);
        jTable_servicios_no_confirmados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable_servicios_no_confirmados);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 970, 300));

        jLabel3.setText("Resultados en un rango de");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, -1, -1));

        jLabel4.setText("días en adelante.");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, -1, -1));

        jButton_excel.setBackground(new java.awt.Color(153, 153, 153));
        jButton_excel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_excel.setText("Excel");
        jButton_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_excelActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_excel, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 415, 80, 30));

        jCheckBox_incluir_noHotel.setText("Incluir resultados donde no aparezca el 'Nombre' del hotel");
        jPanel1.add(jCheckBox_incluir_noHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_days_aheadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_days_aheadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_days_aheadActionPerformed

    private void jButton_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_filtrarActionPerformed
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_jButton_filtrarActionPerformed

    private void jTextField_days_aheadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_days_aheadKeyPressed
        // TODO add your handling code here:
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            filtrar();
        }
    }//GEN-LAST:event_jTextField_days_aheadKeyPressed

    private void jButton_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_excelActionPerformed
        // TODO add your handling code here:
        LocalDate today = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formato_Tab = DateTimeFormatter.ofPattern("d MMMM");

        String dias = jTextField_days_ahead.getText();
        String head = "RESERVA HOTEL PENDIENTE DE CONFIRMACIÓN - " + today.format(formato);
        String footer = "(" + dias + " días en adelante)";

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
                    Util.exportToEXEL(jTable_servicios_no_confirmados, null, file, today.format(formato_Tab), null, head, null, footer, null);
                } catch (WriteException ex) {
                    System.err.println("Error: " + ex);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo crear el archivo " + file.getAbsolutePath());
        }


    }//GEN-LAST:event_jButton_excelActionPerformed

    private void jButton_actualizarServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarServActionPerformed
        // TODO add your handling code here:
        String descripcion = jTextField_descripcionServ.getText();
        String hotel = jTextField_hotel.getText();
        String confirmacion = jTextField_confirmacion.getText().toUpperCase();
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DATE);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int anio = calendar.get(Calendar.YEAR);
        String fechaModificado = dia + "/" + mes + "/" + anio;
        int validarServ = 0;

        //validando campos info servicio
        if (descripcion.equals("")) {
            JOptionPane.showMessageDialog(null, "Asegúrese de llenar correctamente el campo 'Descripcion'.");
            validarServ = 1;
        }

        if (validarServ == 0) {//actualizando BD tabla Servicios

            String historial_modificaciones = "";

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select Historial_modificaciones from servicios where "
                        + "Id_servicio = '" + Id_servicioSeleccionado + "'");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    historial_modificaciones = rs.getString("Historial_modificaciones");
                }

                if (historial_modificaciones == null) {
                    historial_modificaciones = "";
                }
                historial_modificaciones += fechaModificado + " - " + Login.user + "\n";

                pst = cn.prepareStatement("update Servicios set Descripcion=?, Hotel=?, No_conf=?, "
                        + "Historial_modificaciones=? where Id_servicio = '" + Id_servicioSeleccionado + "'");

                pst.setString(1, descripcion);
                pst.setString(2, hotel);
                pst.setString(3, confirmacion);
                pst.setString(4, historial_modificaciones);
                
                pst.executeUpdate();

                cn.close();
                JOptionPane.showMessageDialog(null, "Información del servicio actualizada correctamente.");
                jDialog_Servicio_actualizar.dispose();
                filtrar();
            } catch (SQLException e) {
                System.err.println("Error al registrar datos en tabla servicios: " + e);
                JOptionPane.showMessageDialog(null, "!!Error al registrar datos en la BD. Contacte al administrador.");
            }

        }
    }//GEN-LAST:event_jButton_actualizarServActionPerformed

    private void jTextField_reservadoPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_reservadoPorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_reservadoPorActionPerformed

    private void jButton_historialModificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_historialModificacionesActionPerformed
        // TODO add your handling code here:
        String historial_modificaciones = "";

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select Historial_modificaciones from servicios where "
                    + "Id_servicio = '" + Id_servicioSeleccionado + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                historial_modificaciones = rs.getString("Historial_modificaciones");
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error accediendo a tabla servicios: " + e);
        }

        if (historial_modificaciones == null || historial_modificaciones == "") {
            historial_modificaciones = "No se ha realizado ninguna modificación a esta reserva.";
        }

        JOptionPane.showMessageDialog(null, "Historial de modificaciones:\n\n" + historial_modificaciones);
    }//GEN-LAST:event_jButton_historialModificacionesActionPerformed

    private void jTextField_aloj_efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_aloj_efectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_aloj_efectivoActionPerformed

    private void jTextField_trf_efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_trf_efectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_trf_efectivoActionPerformed

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
            java.util.logging.Logger.getLogger(Hotel_sin_confirmar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hotel_sin_confirmar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hotel_sin_confirmar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hotel_sin_confirmar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hotel_sin_confirmar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizarServ;
    private javax.swing.JButton jButton_excel;
    private javax.swing.JButton jButton_filtrar;
    private javax.swing.JButton jButton_historialModificaciones;
    private javax.swing.JCheckBox jCheckBox_incluir_noHotel;
    private javax.swing.JComboBox<String> jComboBox_destino;
    private javax.swing.JComboBox<String> jComboBox_proveedorTRF;
    private javax.swing.JComboBox<String> jComboBox_trf;
    private com.toedter.calendar.JDateChooser jDateChooser_fechaIda;
    private com.toedter.calendar.JDateChooser jDateChooser_fechaRegreso;
    private javax.swing.JDialog jDialog_Servicio_actualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JLabel jLabel_footer1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_servicios_no_confirmados;
    private javax.swing.JTextArea jTextArea1_observacionesCliente;
    private javax.swing.JTextArea jTextArea1_observacionesServicio;
    private javax.swing.JTextArea jTextArea1_tarjetas_credito;
    private javax.swing.JTextField jTextField_EmailCliente;
    private javax.swing.JTextField jTextField_TEaloj;
    private javax.swing.JTextField jTextField_TEtrf;
    private javax.swing.JTextField jTextField_aloj_credito;
    private javax.swing.JTextField jTextField_aloj_efectivo;
    private javax.swing.JTextField jTextField_apellidoCliente;
    private javax.swing.JTextField jTextField_celularCliente;
    private javax.swing.JTextField jTextField_confirmacion;
    private javax.swing.JTextField jTextField_days_ahead;
    private javax.swing.JTextField jTextField_descripcionServ;
    private javax.swing.JTextField jTextField_hotel;
    private javax.swing.JTextField jTextField_infante;
    private javax.swing.JTextField jTextField_menor;
    private javax.swing.JTextField jTextField_nacionalidadCliente;
    private javax.swing.JTextField jTextField_nombreCliente;
    private javax.swing.JTextField jTextField_pax;
    private javax.swing.JTextField jTextField_reservadoFecha;
    private javax.swing.JTextField jTextField_reservadoPor;
    private javax.swing.JTextField jTextField_telefonoCliente;
    private javax.swing.JTextField jTextField_trf_credito;
    private javax.swing.JTextField jTextField_trf_efectivo;
    // End of variables declaration//GEN-END:variables

    //método para fijar ancho columna Id
    public void FijarAnchoColumnas() {
        TableColumn columnaID = jTable_servicios_no_confirmados.getColumn("Id");
        columnaID.setPreferredWidth(30);
        TableColumn columnaTRF = jTable_servicios_no_confirmados.getColumn("TRF");
        columnaTRF.setPreferredWidth(30);
        TableColumn columnaDesc = jTable_servicios_no_confirmados.getColumn("Descripción");
        columnaDesc.setPreferredWidth(150);
        TableColumn columnaAd = jTable_servicios_no_confirmados.getColumn("Pax");
        columnaAd.setPreferredWidth(60);
        TableColumn columnaTE = jTable_servicios_no_confirmados.getColumn("TE Hotel");
        columnaTE.setPreferredWidth(60);
        TableColumn columnaCLiente = jTable_servicios_no_confirmados.getColumn("Cliente");
        columnaCLiente.setPreferredWidth(120);
    }

    private void filtrar() {

        Util.VaciarTabla(model);

        int days = 0;
        if (Util.EsInt(jTextField_days_ahead.getText()) && Util.IsEmpty(jTextField_days_ahead.getText()) == false) {
            days = Integer.parseInt(jTextField_days_ahead.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Debes especificar los 'días en adelante' correctamente.");
            return;
        }

//        System.out.println("Days ahead: " + days);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String query = "";
        if (jCheckBox_incluir_noHotel.isSelected()) {
            query = "select Id_servicio, Descripcion, TE_alojamiento, Nombre_cliente, "
                    + "Apellido_cliente, Pax, Menores, Infantes, Hotel, No_conf, Fecha_inicio, Fecha_fin, "
                    + "Traslado, Observaciones from servicios where No_conf ='' and Traslado!='Solo_traslado' and "
                    + "Fecha_inicio = '";
        } else {
            query = "select Id_servicio, Descripcion, TE_alojamiento, Nombre_cliente, "
                    + "Apellido_cliente, Pax, Menores, Infantes, Hotel, No_conf, Fecha_inicio, Fecha_fin, "
                    + "Traslado, Observaciones from servicios where No_conf ='' and Traslado!='Solo_traslado' and "
                    + "Hotel != '' and Fecha_inicio = '";
        }

        try {
            Connection cn = Conexion.conectar();

            for (int i = 1; i <= days; i++) {
                LocalDate day = today.plusDays(i);

//                System.out.println("Iterando día: " + day.format(formato));
                PreparedStatement pst = cn.prepareStatement(query + day.format(formato) + "'");

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    Object[] fila = new Object[11];

                    for (int y = 0; y < 11; y++) {
                        if (y < 3) {
                            fila[y] = rs.getObject(y + 1);
                        }
                        if (y == 3) {
                            fila[y] = rs.getString(y + 1);
                            fila[y] += " " + rs.getString(y + 2);
                        }
                        if (y == 4) {
                            fila[y] = rs.getString(y + 2);
                            if (!rs.getString(y + 3).equals("0")) {
                                fila[y] += "+" + rs.getString(y + 3);
                            }
                            if (!rs.getString(y + 4).equals("0")) {
                                fila[y] += "+" + rs.getString(y + 4) + " bb";
                            }
                        }
                        if (y > 4) {
                            fila[y] = rs.getObject(y + 4);
                        }
                    }
                    model.addRow(fila);
                }
            }

            cn.close();

        } catch (SQLException e) {
            System.err.println("Error al llenar tabla. " + e);
            JOptionPane.showMessageDialog(null, "!!Error al mostrar tabla...contacte al administrador");
        }
    }
}
