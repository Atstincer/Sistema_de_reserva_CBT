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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ALEX
 */
public class Servicios_asociados extends javax.swing.JFrame {

    int Id_cliente;
    private DefaultTableModel mode1 = new DefaultTableModel();

    /**
     * Creates new form Clientes_AdminII
     */
    public Servicios_asociados() {
        initComponents();
        setSize(1000, 500);
        setResizable(false);
        setTitle("Tabla servicios - " + Login.user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        Id_cliente = Clientes_update.Id_cliente;
        String nombreCliente = "";
        String apellidoCliente = "";

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("Select Nombre, Apellido from clientes where Id_cliente = '"
                    + "" + Id_cliente + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nombreCliente = rs.getString("Nombre");
                apellidoCliente = rs.getString("Apellido");
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error conectando con BD tabla clientes: " + e);
        }

        jLabel_encabezado.setText("Servicios asociados a: " + nombreCliente + " " + apellidoCliente);

        jTable_servicios = new JTable(mode1);
        jScrollPane1.setViewportView(jTable_servicios);

        mode1.addColumn(" ");
        mode1.addColumn("Descripción");
        mode1.addColumn("TE alojamiento");
        mode1.addColumn("Ad");
        mode1.addColumn("Men");
        mode1.addColumn("Inf");
        mode1.addColumn("Hotel");
        mode1.addColumn("Destino");
        mode1.addColumn("Conf.");
        mode1.addColumn("Desde");
        mode1.addColumn("Hasta");
        mode1.addColumn("Traslado?");
        mode1.addColumn("TE traslado");
        mode1.addColumn("Observaciones");

        FijarAnchoColumnas();

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select Id_servicio, Descripcion, TE_alojamiento, Pax, "
                    + "Menores, Infantes, Hotel, Destino, No_conf, Fecha_inicio, Fecha_fin, Traslado, TE_traslado, "
                    + "Observaciones, CXX from servicios where Id_cliente = '" + Id_cliente + "'");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[14];
                String cxx = "";
                for (int i = 0; i < 15; i++) {
                    if(i<14){
                        fila[i] = rs.getString(i + 1);
                    }else{
                        cxx = rs.getString(i+1);
                    }                    
                }
                if(cxx.equals("SI")){
                    fila[13] += "- CXX";
                }else if(cxx.equals("SI_aloj")){
                    fila[13] += "- CXX Hotel";
                }else if(cxx.equals("SI_trf")){
                    fila[13] += "- CXX TRF";
                }
                mode1.addRow(fila);
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error al llenar tabla. " + e);
            JOptionPane.showMessageDialog(null, "!!Error: " + e.getMessage().toString());
        }

        Color myColorFondo = new Color(204, 204, 204);
        Color myColorFuente = new Color(0, 0, 115);
        jTable_servicios.setBackground(myColorFondo);
        jTable_servicios.setForeground(myColorFuente);

//        jTable_servicios.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int fila_point = jTable_servicios.rowAtPoint(e.getPoint());
//                int columna_point = 1;
//
//                if (fila_point > -1) {
//                    Id_servicioSeleccionado = (int) mode1.getValueAt(fila_point, 0);
////                    Servicios_update servicio_update = new Servicios_update();
////                    servicio_update.setVisible(true);                   
//                    System.out.println("Id servicio seleccionado: " + Id_servicioSeleccionado);
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
        jLabel_encabezado = new javax.swing.JLabel();
        jLabel_footer = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_servicios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_encabezado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jLabel_encabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 43, -1, -1));

        jLabel_footer.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 150, 20));

        jTable_servicios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable_servicios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_servicios.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_servicios.setCellSelectionEnabled(true);
        jTable_servicios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable_servicios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 970, 300));

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
            java.util.logging.Logger.getLogger(Servicios_asociados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servicios_asociados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servicios_asociados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servicios_asociados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servicios_asociados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_encabezado;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_servicios;
    // End of variables declaration//GEN-END:variables

    //método para fijar ancho columna Id
    public void FijarAnchoColumnas() {
        TableColumn columnaId = jTable_servicios.getColumn(" ");
        columnaId.setMinWidth(35);
        columnaId.setMaxWidth(35);
        TableColumn columnaAd = jTable_servicios.getColumn("Ad");
        columnaAd.setMinWidth(30);
        columnaAd.setMaxWidth(30);
        TableColumn columnaMen = jTable_servicios.getColumn("Men");
        columnaMen.setMinWidth(35);
        columnaMen.setMaxWidth(35);
        TableColumn columnaInf = jTable_servicios.getColumn("Inf");
        columnaInf.setMinWidth(30);
        columnaInf.setMaxWidth(30);
    }

}
