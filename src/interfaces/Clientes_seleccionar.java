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
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ALEX
 */
public class Clientes_seleccionar extends javax.swing.JFrame {

    DefaultTableModel mode1 = new DefaultTableModel();
    String telefono, cell, mail, nombre, apellido, query;
    String[] servicio = new String[25];
    int Id_cliente_vincular;

    /**
     * Creates new form Clientes_AdminII
     */
    public Clientes_seleccionar() {
        initComponents();
        setSize(710, 500);
        setResizable(false);
        setTitle("Seleccionar cliente - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JOptionPane.showMessageDialog(null, "El 'teléfono fijo', el 'celular' ó el 'Email' ingresados coinciden con uno "
                + "o más clientes registrados ya con anterioridad. Seleccione por favor de la tabla el cliente que desea "
                + "vicular a este servicio.");

        telefono = Servicios_reservar.telefono;
        cell = Servicios_reservar.cell;
        mail = Servicios_reservar.mail;

        try {//llenado tabla con coincidencia de clientes en Servicio_reservar
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(InsertarQuery());

            ResultSet rs = pst.executeQuery();

            jTable_clientes = new JTable(mode1);
            jScrollPane1.setViewportView(jTable_clientes);

            mode1.addColumn(" ");
            mode1.addColumn("Nombre");
            mode1.addColumn("Apellido");
            mode1.addColumn("Nacionalidad");
            mode1.addColumn("Telefono");
            mode1.addColumn("Mobil");
            mode1.addColumn("E-mail");
            mode1.addColumn("Observaciones");

            FijarAnchoId();

            while (rs.next()) {
                Object[] fila = new Object[8];

                for (int i = 0; i < 8; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                mode1.addRow(fila);
            }
            cn.close();

        } catch (SQLException e) {
            System.err.println("Error al llenar tabla. " + e);
            JOptionPane.showMessageDialog(null, "!!Error al mostrar tabla...contacte al administrador");
        }
        
        Color myColorFondo = new Color(204,204,204);
        Color myColorFuente = new Color(0,0,115);
        jTable_clientes.setBackground(myColorFondo);
        jTable_clientes.setForeground(myColorFuente);

        jTable_clientes.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = jTable_clientes.rowAtPoint(e.getPoint());
                int columna_point = 1;

                if (fila_point > -1) {
                    Id_cliente_vincular = (int) mode1.getValueAt(fila_point, 0);

                    int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro que desea vincular el cliente \n"
                            + "seleccionado al servicio?");

                    if (confirmacion == JOptionPane.OK_OPTION) {

                        try {//recuperando nombre y apellidos del cliente seleccionado
                            Connection cn = Conexion.conectar();
                            PreparedStatement pst = cn.prepareStatement("select Nombre, Apellido from Clientes where "
                                    + "Id_cliente = '" + Id_cliente_vincular + "'");

                            ResultSet rs = pst.executeQuery();

                            if (rs.next()) {
                                nombre = rs.getString("Nombre");
                                apellido = rs.getString("Apellido");
                            }
                            
                            cn.close();

                        } catch (SQLException er) {
                            System.err.println("Error capturando nombre y apellido tabla Clientes: " + er);
                            JOptionPane.showMessageDialog(null, "Error conectando con la BD. Contacte al administrador.");
                        }

                        Servicios_reservar servicios_reservar = new Servicios_reservar();
                        servicio = servicios_reservar.servicio;
                        servicio[0] = String.valueOf(Id_cliente_vincular);                        
                        servicio[16] = nombre;
                        servicio[19] = apellido;

                        try {//registrando datos en tabla servicio
                            Connection cn2 = Conexion.conectar();
                            PreparedStatement pst2 = cn2.prepareStatement("insert into servicios values "
                                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                            pst2.setInt(1, 0);

                            for (int i = 0; i < servicio.length; i++) {
                                if(i>=20 && i<=23){
                                    pst2.setDouble(i + 2, Double.parseDouble(servicio[i]));
                                }else{
                                    pst2.setString(i + 2, servicio[i]);
                                }                                                               
                            }
                            

                            pst2.executeUpdate();

                            cn2.close();
                            JOptionPane.showMessageDialog(null, "Servicio reservado correctamente.");
                            dispose();

                        } catch (SQLException s) {
                            System.err.println("Error llenando tabla servicios: " + s);
                            JOptionPane.showMessageDialog(null, "Error llenando tabla servicios. Por favor contacte \n"
                                    + "al administrador.");
                        }
                    }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_buscar = new javax.swing.JTextField();
        jComboBox_campos = new javax.swing.JComboBox<>();
        jButton_filtrar = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_clientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Seleccione el cliente que desea vincular al servicio");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 45, -1, -1));

        jTextField_buscar.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_buscar.setForeground(new java.awt.Color(0, 0, 155));
        jTextField_buscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_buscarActionPerformed(evt);
            }
        });
        jTextField_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_buscarKeyPressed(evt);
            }
        });
        jPanel1.add(jTextField_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 150, 30));

        jComboBox_campos.setBackground(new java.awt.Color(153, 153, 153));
        jComboBox_campos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Nombre", "Telefono_fijo", "Telefono_cell", "Email" }));
        jPanel1.add(jComboBox_campos, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 90, 30));

        jButton_filtrar.setBackground(new java.awt.Color(153, 153, 153));
        jButton_filtrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_filtrar.setText("Filtrar");
        jButton_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_filtrarActionPerformed(evt);
            }
        });
        jButton_filtrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_filtrarKeyPressed(evt);
            }
        });
        jPanel1.add(jButton_filtrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 415, 80, 30));

        jLabel_footer.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 450, 150, 20));

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 153));

        jTable_clientes.setBackground(new java.awt.Color(153, 153, 153));
        jTable_clientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable_clientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_clientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_clientes.setCellSelectionEnabled(true);
        jTable_clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable_clientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 690, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(369, 369, 369))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(421, 421, 421))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_buscarActionPerformed

    private void jButton_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_filtrarActionPerformed
        // TODO add your handling code here:
        Filtrar();
    }//GEN-LAST:event_jButton_filtrarActionPerformed

    private void jButton_filtrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_filtrarKeyPressed
        // TODO add your handling code here:
         if(evt.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            Filtrar();
        }
    }//GEN-LAST:event_jButton_filtrarKeyPressed

    private void jTextField_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_buscarKeyPressed
        // TODO add your handling code here:
         if(evt.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            Filtrar();
        }
    }//GEN-LAST:event_jTextField_buscarKeyPressed

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
            java.util.logging.Logger.getLogger(Clientes_seleccionar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes_seleccionar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes_seleccionar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes_seleccionar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Clientes_seleccionar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_filtrar;
    private javax.swing.JComboBox<String> jComboBox_campos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_clientes;
    private javax.swing.JTextField jTextField_buscar;
    // End of variables declaration//GEN-END:variables

    //método para vaciar tabla
    public void VaciarTabla() {
        for (int i = mode1.getRowCount() - 1; i >= 0; i--) {
            mode1.removeRow(i);
        }
    }

    //método para fijar ancho columna Id
    public void FijarAnchoId() {
        TableColumn columnaId = jTable_clientes.getColumn(" ");
        columnaId.setMinWidth(25);
        columnaId.setMaxWidth(25);
    }

//Metodo para determinar que mostrar en la tabla inicialmente
    public String InsertarQuery() {
        if (!telefono.equals("") && cell.equals("") && mail.equals("")) {//buscar solo por telefono
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, Telefono_cell, E_mail, Observaciones "
                    + "from Clientes where Telefono_fijo = '" + telefono + "'";
        } else if (!telefono.equals("") && !cell.equals("") && mail.equals("")) {//buscar por telefono y cell
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, Telefono_cell, E_mail, Observaciones "
                    + "from Clientes where Telefono_fijo = '" + telefono + "' or  Telefono_cell = '" + cell + "'";
        } else if (!telefono.equals("") && cell.equals("") && !mail.equals("")) {//buscar por telefono y mail
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, Telefono_cell, E_mail, Observaciones "
                    + "from Clientes where Telefono_fijo = '" + telefono + "' or  E_mail = '" + mail + "'";
        } else if (!telefono.equals("") && !cell.equals("") && !mail.equals("")) {//buscar por los tres
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, Telefono_cell, E_mail, Observaciones "
                    + "from Clientes where Telefono_fijo = '" + telefono + "' or  Telefono_cell = '" + cell + "' "
                    + "or E_mail = '" + mail + "'";
        } else if (telefono.equals("") && !cell.equals("") && mail.equals("")) {//buscar por cell
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, Telefono_cell, E_mail, Observaciones "
                    + "from Clientes where Telefono_cell = '" + cell + "'";
        } else if (telefono.equals("") && !cell.equals("") && !mail.equals("")) {//buscar por cell y mail
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, Telefono_cell, E_mail, Observaciones "
                    + "from Clientes where Telefono_cell = '" + cell + "' or E_mail = '" + mail + "'";
        } else if (telefono.equals("") && cell.equals("") && !mail.equals("")) {//buscar por mail
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, Telefono_cell, E_mail, Observaciones "
                    + "from Clientes where E_mail = '" + mail + "'";
        }
        return query;
    }
    
    public void Filtrar(){
        int campo = jComboBox_campos.getSelectedIndex();
        String abuscar = jTextField_buscar.getText().trim();
        String query = "";

        if (campo == 0) {//buscar "Todos";            
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, Telefono_cell, E_mail, Observaciones "
                    + "from Clientes";
            jTextField_buscar.setText("");
        } else if (campo == 1) {//buscar por "Nombre";            
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, "
                    + "Telefono_cell, E_mail, Observaciones from Clientes where Nombre = '" + abuscar + "'";
        } else if (campo == 2) {//buscar por "Telefono_fijo";            
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, "
                    + "Telefono_cell, E_mail, Observaciones from Clientes where Telefono_fijo = '" + abuscar + "'";
        } else if (campo == 3) {//buscar por "Telefono_cell";            
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, "
                    + "Telefono_cell, E_mail, Observaciones from Clientes where Telefono_cell = '" + abuscar + "'";
        } else if (campo == 4) {//buscar por "E_mail";            
            query = "select Id_cliente, Nombre, Apellido, Nacionalidad, Telefono_fijo, "
                    + "Telefono_cell, E_mail, Observaciones from Clientes where E_mail = '" + abuscar + "'";
        }
        VaciarTabla();

        try {//filtrando tabla clientes
            Connection cn2 = Conexion.conectar();
            PreparedStatement pst = cn2.prepareStatement(query);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[8];

                for (int i = 0; i < 8; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                mode1.addRow(fila);
            }
            cn2.close();

        } catch (SQLException e) {
            System.err.println("Error al filtrar tabla clientes: " + e);
            JOptionPane.showMessageDialog(null, "!!Error al filtrar...comuníquese con el administrador");
        }
    }

}
