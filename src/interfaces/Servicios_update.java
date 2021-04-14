/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import clases.Conexion;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.JTextField;

/**
 *
 * @author ALEX
 */
public class Servicios_update extends javax.swing.JFrame {

    private String Id_servicio;

    /**
     * Creates new form Servicios_agregar_modificar
     */
    public Servicios_update() {
        initComponents();
        setSize(740, 625);
        setResizable(false);
        setTitle("Actualizar servicio - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Id_servicio = String.valueOf(Servicios_tablaFiltro.Id_servicioSeleccionado);
        String Id_cliente = "", desde = "", hasta = "", nombre = "", apellido = "";
        
        Color colorJDateFondo = new Color(204,204,204);        
        ((JTextField)jDateChooser_fechaIda.getDateEditor().getUiComponent()).setBackground(colorJDateFondo);
        ((JTextField)jDateChooser_fechaRegreso.getDateEditor().getUiComponent()).setBackground(colorJDateFondo); 

        try {//descargando datos del servicio
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select Id_cliente, Descripcion, TE_alojamiento, Hotel, Destino, "
                    + "Fecha_inicio, Fecha_fin, Traslado, TE_traslado, Reservado_fecha, Reservado_por, Observaciones, "
                    + "Proveedor_trf, No_conf, Pax, Nombre_cliente, Menores, Infantes, Apellido_cliente, Efectivo_hotel, "
                    + "Efectivo_trf, Credito_hotel, Credito_trf, Tarjeta_credito from servicios where Id_servicio = '"
                    + "" + Id_servicio + "'");

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
        } catch (SQLException e) {
            System.err.println("Error al intentar obtener datos del servicio: " + e);
            JOptionPane.showMessageDialog(null, "Error al conectarse con la BD. Contacte al administrador.");
        }

//        if (historial_modificaciones == null) {
//            historial_modificaciones = "";
//        }
        try {//colocando fechas en jdatechooser
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha_desde = formato.parse(desde);
            Date fecha_hasta = formato.parse(hasta);
            jDateChooser_fechaIda.setDate(fecha_desde);
            jDateChooser_fechaRegreso.setDate(fecha_hasta);
        } catch (ParseException e) {
            System.err.println("Error parseando fecha: " + e);
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
            } catch (SQLException e) {
                System.err.println("Error accediendo tabla clientes en constructor: " + e);
                JOptionPane.showMessageDialog(null, "!!Error conectando con BD. Contacte al administrador.");
            }
        } else {
            jTextField_nombreCliente.setText(nombre);
            jTextField_apellidoCliente.setText(apellido);
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
        jLabel2 = new javax.swing.JLabel();
        jTextField_pax = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_descripcionServ = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_hotel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_TEaloj = new javax.swing.JTextField();
        jComboBox_destino = new javax.swing.JComboBox<>();
        jComboBox_trf = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_TEtrf = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox_proveedorTRF = new javax.swing.JComboBox<>();
        jButton_actualizarServ = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField_nombreCliente = new javax.swing.JTextField();
        jTextField_apellidoCliente = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField_nacionalidadCliente = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField_telefonoCliente = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField_celularCliente = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField_EmailCliente = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1_observacionesCliente = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jDateChooser_fechaIda = new com.toedter.calendar.JDateChooser();
        jDateChooser_fechaRegreso = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1_observacionesServicio = new javax.swing.JTextArea();
        jLabel_footer = new javax.swing.JLabel();
        jTextField_confirmacion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField_reservadoFecha = new javax.swing.JTextField();
        jTextField_reservadoPor = new javax.swing.JTextField();
        jButton_historialModificaciones = new javax.swing.JButton();
        jTextField_menor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField_infante = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField_aloj_efectivo = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField_aloj_credito = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1_tarjetas_credito = new javax.swing.JTextArea();
        jTextField_trf_efectivo = new javax.swing.JTextField();
        jTextField_trf_credito = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("INFORMACION GENERAL");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 30, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Adu");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        jTextField_pax.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_pax.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_pax.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_pax, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 105, 30, 30));

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Descripción");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jTextField_descripcionServ.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_descripcionServ.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_descripcionServ.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_descripcionServ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 150, 30));

        jLabel4.setText("Hotel");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 90, -1, -1));

        jTextField_hotel.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_hotel.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_hotel.setAutoscrolls(false);
        jTextField_hotel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_hotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 105, 150, 30));

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("TE alojamiento");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 90, -1, -1));

        jTextField_TEaloj.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_TEaloj.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_TEaloj.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_TEaloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 105, 100, 30));

        jComboBox_destino.setBackground(new java.awt.Color(100, 100, 100));
        jComboBox_destino.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_destino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Otros", "Varadero", "CSM", "CayoCoco", "Cienfuegos", "Trinidad" }));
        jPanel1.add(jComboBox_destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 100, 30));

        jComboBox_trf.setBackground(new java.awt.Color(100, 100, 100));
        jComboBox_trf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_trf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Solo_ida", "Solo_regreso", "Solo_traslado" }));
        jPanel1.add(jComboBox_trf, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 100, 30));

        jLabel6.setText("Destino");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 155, -1, -1));

        jLabel7.setText("Traslado");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 155, -1, -1));

        jLabel8.setText("TE traslado");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 155, -1, -1));

        jTextField_TEtrf.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_TEtrf.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_TEtrf.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_TEtrf, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 100, 30));

        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Desde");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 155, -1, -1));

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Hasta");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 155, -1, -1));

        jLabel11.setText("Proveedor");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 155, -1, -1));

        jComboBox_proveedorTRF.setBackground(new java.awt.Color(100, 100, 100));
        jComboBox_proveedorTRF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Transtur", "Transgvt", "Otro" }));
        jPanel1.add(jComboBox_proveedorTRF, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 90, 30));

        jButton_actualizarServ.setBackground(new java.awt.Color(153, 153, 153));
        jButton_actualizarServ.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_actualizarServ.setText("Actualizar");
        jButton_actualizarServ.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_actualizarServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarServActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_actualizarServ, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 525, 100, 40));

        jLabel13.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 640, -1));

        jLabel14.setText("Observaciones");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("CLIENTE RELACIONADO");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 360, -1, -1));

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("Nombre");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        jTextField_nombreCliente.setEditable(false);
        jTextField_nombreCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_nombreCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_nombreCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_nombreCliente.setFocusTraversalPolicyProvider(true);
        jPanel1.add(jTextField_nombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 405, 100, 30));

        jTextField_apellidoCliente.setEditable(false);
        jTextField_apellidoCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_apellidoCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_apellidoCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_apellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 405, 100, 30));

        jLabel18.setText("Apellido");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, -1, -1));

        jLabel19.setText("Nacionalidad");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 455, -1, -1));

        jTextField_nacionalidadCliente.setEditable(false);
        jTextField_nacionalidadCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_nacionalidadCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_nacionalidadCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_nacionalidadCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, 80, 30));

        jLabel20.setText("(opcional)");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 362, -1, -1));

        jTextField_telefonoCliente.setEditable(false);
        jTextField_telefonoCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_telefonoCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_telefonoCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_telefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 405, 80, 30));

        jLabel21.setText("Teléfono");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, -1, -1));

        jTextField_celularCliente.setEditable(false);
        jTextField_celularCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_celularCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_celularCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_celularCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 405, 80, 30));

        jLabel22.setText("Celular");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, -1, -1));

        jTextField_EmailCliente.setEditable(false);
        jTextField_EmailCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_EmailCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_EmailCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_EmailCliente.setFocusTraversalPolicyProvider(true);
        jPanel1.add(jTextField_EmailCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 210, 30));

        jLabel23.setText("E-mail");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 455, -1, -1));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1_observacionesCliente.setEditable(false);
        jTextArea1_observacionesCliente.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1_observacionesCliente.setColumns(1);
        jTextArea1_observacionesCliente.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1_observacionesCliente.setForeground(new java.awt.Color(0, 0, 115));
        jTextArea1_observacionesCliente.setRows(5);
        jScrollPane2.setViewportView(jTextArea1_observacionesCliente);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 405, 180, 100));

        jLabel24.setText("Observaciones");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, -1, -1));

        jDateChooser_fechaIda.setBackground(new java.awt.Color(153, 153, 153));
        jDateChooser_fechaIda.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateChooser_fechaIda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 130, 30));

        jDateChooser_fechaRegreso.setBackground(new java.awt.Color(153, 153, 153));
        jDateChooser_fechaRegreso.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateChooser_fechaRegreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 130, 30));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1_observacionesServicio.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1_observacionesServicio.setColumns(1);
        jTextArea1_observacionesServicio.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1_observacionesServicio.setForeground(new java.awt.Color(0, 0, 115));
        jTextArea1_observacionesServicio.setRows(5);
        jScrollPane3.setViewportView(jTextArea1_observacionesServicio);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 235, 180, 100));

        jLabel_footer.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 575, 150, 20));

        jTextField_confirmacion.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_confirmacion.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_confirmacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_confirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 105, 80, 30));

        jLabel16.setText("Confirmación");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, -1, -1));

        jLabel25.setText("Reservado:");
        jLabel25.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 70, -1));

        jTextField_reservadoFecha.setEditable(false);
        jTextField_reservadoFecha.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_reservadoFecha.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_reservadoFecha.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_reservadoFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 238, 80, 30));

        jTextField_reservadoPor.setEditable(false);
        jTextField_reservadoPor.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_reservadoPor.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_reservadoPor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_reservadoPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_reservadoPorActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_reservadoPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 278, 80, 30));

        jButton_historialModificaciones.setBackground(new java.awt.Color(153, 153, 153));
        jButton_historialModificaciones.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_historialModificaciones.setText("Historial modificaciones");
        jButton_historialModificaciones.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_historialModificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_historialModificacionesActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_historialModificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, 150, 25));

        jTextField_menor.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_menor.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_menor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_menor, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 105, 30, 30));

        jLabel12.setText("Niños");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, -1, -1));

        jTextField_infante.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_infante.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_infante.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_infante, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 105, 30, 30));

        jLabel26.setText("Infante");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, -1, -1));

        jLabel27.setForeground(new java.awt.Color(255, 0, 0));
        jLabel27.setText("- campos obligatorios");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

        jLabel28.setText("Alojamiento");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jTextField_aloj_efectivo.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_aloj_efectivo.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_aloj_efectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_aloj_efectivo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_aloj_efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_aloj_efectivoActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_aloj_efectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 245, 80, 25));

        jLabel29.setText("Efectivo");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        jTextField_aloj_credito.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_aloj_credito.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_aloj_credito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_aloj_credito.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_aloj_credito, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 245, 80, 25));

        jLabel30.setText("Crédito");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        jLabel31.setText("Tarjetas de crédito");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, -1));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1_tarjetas_credito.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1_tarjetas_credito.setColumns(1);
        jTextArea1_tarjetas_credito.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1_tarjetas_credito.setForeground(new java.awt.Color(0, 0, 115));
        jTextArea1_tarjetas_credito.setRows(5);
        jScrollPane4.setViewportView(jTextArea1_tarjetas_credito);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 245, 150, 60));

        jTextField_trf_efectivo.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_trf_efectivo.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_trf_efectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_trf_efectivo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_trf_efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_trf_efectivoActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_trf_efectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 80, 25));

        jTextField_trf_credito.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_trf_credito.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_trf_credito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_trf_credito.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(jTextField_trf_credito, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 80, 25));

        jLabel32.setText("Traslado");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 285, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_actualizarServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarServActionPerformed
        // TODO add your handling code here:
        String descripcion = jTextField_descripcionServ.getText();
        String hotel = jTextField_hotel.getText();
        String adultos = jTextField_pax.getText();
        String menores = jTextField_menor.getText();
        String infante = jTextField_infante.getText();
        String te_alojamiento = jTextField_TEaloj.getText();
        String destino = jComboBox_destino.getSelectedItem().toString();
        String te_traslado = jTextField_TEtrf.getText();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha_desde = jDateChooser_fechaIda.getDate();
        Date fecha_hasta = jDateChooser_fechaRegreso.getDate();
        String desde = "";
        String hasta = "";
        if (fecha_desde != null) {
            desde = formato.format(fecha_desde);
        } else {
            desde = "";
        }
        if (fecha_hasta != null) {
            hasta = formato.format(fecha_hasta);
        } else {
            hasta = "";
        }
        String proveedorTRF = jComboBox_proveedorTRF.getSelectedItem().toString();
        String traslado = jComboBox_trf.getSelectedItem().toString();
        String observaciones_serv = jTextArea1_observacionesServicio.getText();
        String confirmacion = jTextField_confirmacion.getText().toUpperCase();
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DATE);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int anio = calendar.get(Calendar.YEAR);
        String fechaModificado = dia + "/" + mes + "/" + anio;
        int validarServ = 0;

        String aloj_efectivo = jTextField_aloj_efectivo.getText();
        if (!aloj_efectivo.equals("")) {
            if (!EsDouble(aloj_efectivo)) {
                JOptionPane.showMessageDialog(null, "Campo 'alojamiento efectivo' admite solamente números.");
                validarServ = 1;
            }
        } else {
            aloj_efectivo = "0";
        }
        String aloj_credito = jTextField_aloj_credito.getText();
        if (!aloj_credito.equals("")) {
            if (!EsDouble(aloj_credito)) {
                JOptionPane.showMessageDialog(null, "Campo 'alojamiento crédito' admite solamente números.");
                validarServ = 1;
            }
        } else {
            aloj_credito = "0";
        }
        String trf_efectivo = jTextField_trf_efectivo.getText();
        if (!trf_efectivo.equals("")) {
            if (!EsDouble(trf_efectivo)) {
                JOptionPane.showMessageDialog(null, "Campo 'traslado efectivo' admite solamente números.");
                validarServ = 1;
            }
        } else {
            trf_efectivo = "0";
        }
        String trf_credito = jTextField_trf_credito.getText();
        if (!trf_credito.equals("")) {
            if (!EsDouble(trf_credito)) {
                JOptionPane.showMessageDialog(null, "Campo 'traslado crédito' admite solamente números.");
                validarServ = 1;
            }
        } else {
            trf_credito = "0";
        }
        String tarjetas_credito = jTextArea1_tarjetas_credito.getText().toUpperCase();

//        System.out.print("H efectivo: " + aloj_efectivo + ", credito: " + aloj_credito + "\n"
//                + "TRF efectivo: " + trf_efectivo + ", credito: " + trf_credito + "\n");

        //validando campos info servicio
        if (descripcion.equals("") || desde.equals("") || hasta.equals("")) {
            JOptionPane.showMessageDialog(null, "Asegúrese de llenar correctamente los campos 'Descripcion', \n"
                    + "'Desde' y 'Hasta'.");
            validarServ = 1;
        }

        if (!adultos.equals("")) {//validando campo adultos
            if (!EsInt(adultos)) {
                JOptionPane.showMessageDialog(null, "El campo 'adultos' admite solo números enteros.");
                validarServ = 1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Asegúrese de llenar correctamente el campo 'adultos'.");
            validarServ = 1;
        }

        if (!menores.equals("")) {//validando campo menores
            if (!EsInt(menores)) {
                JOptionPane.showMessageDialog(null, "El campo 'niños' admite solo números enteros.");
                validarServ = 1;
            }
        } else {
            menores = "0";
        }

        if (!infante.equals("")) {//validando campo infante
            if (!EsInt(infante)) {
                JOptionPane.showMessageDialog(null, "El campo 'infante' admite solo números enteros.");
                validarServ = 1;
            }
        } else {
            infante = "0";
        }

        //validando campo TE_traslado, trf efectivo y crédito
        if (traslado.equals("No")) {
            proveedorTRF = "";
            te_traslado = "";
            trf_efectivo = "0";
            trf_credito = "0";
            jTextField_TEtrf.setText("");
            jTextField_trf_efectivo.setText("");
            jTextField_trf_credito.setText("");
        } else {
            if (te_traslado.equals("") || (trf_efectivo.equals("0") && trf_credito.equals("0"))) {
                JOptionPane.showMessageDialog(null, "Asegúrese de llenar correctamente el campo 'TE traslado'\n"
                        + "y los valores de efectivo/crédito correspondientes.");
                validarServ = 1;
            }
        }

        //validando campo TE_alojamiento, alojamiento efectivo y crédito
        if (traslado.equals("Solo_traslado")) {
            te_alojamiento = "";
            aloj_efectivo = "0";
            aloj_credito = "0";
            jTextField_TEaloj.setText("");
            jTextField_aloj_efectivo.setText("");
            jTextField_aloj_credito.setText("");
        } else {
            if (te_alojamiento.equals("") || (aloj_efectivo.equals("0") && aloj_credito.equals("0"))) {
                JOptionPane.showMessageDialog(null, "Asegúrese de llenar correctamente el campo 'TE_alojamiento'\n"
                        + "y los valores de efectivo/crédito correspondientes.");
                validarServ = 1;
            }
        }

        //comprobando existencia de TE_alojamiento ó TE_traslado en BD
        if (!te_alojamiento.equals("") || !te_traslado.equals("")) {
            try {
                Connection cn2 = Conexion.conectar();
                PreparedStatement pst2 = cn2.prepareStatement(QueryVerificarCoincidenciaTE(te_alojamiento, te_traslado));

                ResultSet rs2 = pst2.executeQuery();

                while (rs2.next()) {
                    String Id_servicio_encontrado = rs2.getString("Id_servicio");
                    if (!Id_servicio_encontrado.equals(Id_servicio)) {
                        JOptionPane.showMessageDialog(null, "El ticket de alojamiento o de traslado coincide con los datos \n"
                                + "de algún otro servicio reservado con anterioridad. Chequee por favor.");
                        validarServ = 1;
                    }
                }

                cn2.close();
            } catch (SQLException e) {
                System.err.println("Error al comprobar existencia en BD de TEalojamiento y TEtraslado: " + e);
                JOptionPane.showMessageDialog(null, "!!Error al conectar con la base de datos. Contacte al administrador.");
            }
        }

        if (validarServ == 0) {//actualizando BD tabla Servicios

            String historial_modificaciones = "";

            try {
                Connection cn3 = Conexion.conectar();
                PreparedStatement pst3 = cn3.prepareStatement("select Historial_modificaciones from servicios where "
                        + "Id_servicio = '" + Id_servicio + "'");

                ResultSet rs3 = pst3.executeQuery();

                if (rs3.next()) {
                    historial_modificaciones = rs3.getString("Historial_modificaciones");
                }

                if (historial_modificaciones == null) {
                    historial_modificaciones = "";
                }
                historial_modificaciones += fechaModificado + " - " + Login.user + "\n";

                pst3 = cn3.prepareStatement("update Servicios set Descripcion=?, TE_alojamiento=?, "
                        + "Hotel=?, Destino=?, Fecha_inicio=?, Fecha_fin=?, Traslado=?, TE_traslado=?, Observaciones=?, "
                        + "Proveedor_trf=?, No_conf=?, Pax=?, Historial_modificaciones=?, Menores=?, Infantes=?, "
                        + "Efectivo_hotel=?, Efectivo_trf=?, Credito_hotel=?, Credito_trf=?, Tarjeta_credito=? "
                        + "where Id_servicio = '" + Id_servicio + "'");

                pst3.setString(1, descripcion);
                pst3.setString(2, te_alojamiento);
                pst3.setString(3, hotel);
                pst3.setString(4, destino);
                pst3.setString(5, desde);
                pst3.setString(6, hasta);
                pst3.setString(7, traslado);
                pst3.setString(8, te_traslado);
                pst3.setString(9, observaciones_serv);
                pst3.setString(10, proveedorTRF);
                pst3.setString(11, confirmacion);
                pst3.setString(12, adultos);
                pst3.setString(13, historial_modificaciones);
                pst3.setString(14, menores);
                pst3.setString(15, infante);
                pst3.setDouble(16, Double.parseDouble(aloj_efectivo));
                pst3.setDouble(17, Double.parseDouble(trf_efectivo));
                pst3.setDouble(18, Double.parseDouble(aloj_credito));
                pst3.setDouble(19, Double.parseDouble(trf_credito));
                pst3.setString(20, tarjetas_credito);

                pst3.executeUpdate();

                cn3.close();
                JOptionPane.showMessageDialog(null, "Información del servicio actualizada correctamente.");
                dispose();
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
            Connection cn3 = Conexion.conectar();
            PreparedStatement pst3 = cn3.prepareStatement("select Historial_modificaciones from servicios where "
                    + "Id_servicio = '" + Id_servicio + "'");

            ResultSet rs3 = pst3.executeQuery();

            if (rs3.next()) {
                historial_modificaciones = rs3.getString("Historial_modificaciones");
            }
            cn3.close();
        } catch (SQLException e) {
            System.err.println("Error accediendo a tabla servicios: " + e);
        }
        
        if(historial_modificaciones == null){
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
            java.util.logging.Logger.getLogger(Servicios_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servicios_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servicios_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servicios_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Servicios_update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizarServ;
    private javax.swing.JButton jButton_historialModificaciones;
    private javax.swing.JComboBox<String> jComboBox_destino;
    private javax.swing.JComboBox<String> jComboBox_proveedorTRF;
    private javax.swing.JComboBox<String> jComboBox_trf;
    private com.toedter.calendar.JDateChooser jDateChooser_fechaIda;
    private com.toedter.calendar.JDateChooser jDateChooser_fechaRegreso;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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

    //query para verificar si info coincide con algun ticket registrado anteriormente
    public String QueryVerificarCoincidenciaTE(String te_alojamiento, String te_traslado) {
        String query = "";
        if (!te_alojamiento.equals("") && te_traslado.equals("")) {//buscar por te_alojamiento
            query = "select Id_servicio from Servicios where TE_alojamiento = '" + te_alojamiento + "'";
        } else if (!te_alojamiento.equals("") && !te_traslado.equals("")) {//buscar por te_alojamiento y te_traslado
            query = "select Id_servicio from Servicios where TE_alojamiento = '" + te_alojamiento + "' "
                    + "or TE_traslado = '" + te_traslado + "'";
        } else if (te_alojamiento.equals("") && !te_traslado.equals("")) {//buscar por te_traslado
            query = "select Id_servicio from Servicios where TE_traslado = '" + te_traslado + "'";
        }
        return query;
    }

    //comprobando si es número
    public boolean EsDouble(String str) {
        try {
            double db = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //comprobando si es entero
    public boolean EsInt(String a) {
        try {
            int b = Integer.parseInt(a);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}