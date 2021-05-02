/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conexion;
import clases.Util;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEX
 */
public class Cancelar extends javax.swing.JDialog {

    /**
     * Creates new form Cancelados
     */
    public Cancelar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(400, 220);
        setLocationRelativeTo(null);
        setTitle("CANCELANDO COMPROBANTES - " + Login.user);
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
        jTextField_TE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel_footer = new javax.swing.JLabel();
        jButton_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("CANCELAR COMPROBANTE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 25, -1, -1));

        jTextField_TE.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_TE.setForeground(new java.awt.Color(0, 0, 115));
        jTextField_TE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jTextField_TE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_TEKeyPressed(evt);
            }
        });
        jPanel1.add(jTextField_TE, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 75, 100, 25));

        jLabel3.setText("TE:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel_footer.setText("© Realizado por atstincer");
        jPanel1.add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 150, 20));

        jButton_cancelar.setBackground(new java.awt.Color(153, 153, 153));
        jButton_cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_cancelar.setText("CANCELAR");
        jButton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelarActionPerformed(evt);
            }
        });
        jButton_cancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_cancelarKeyPressed(evt);
            }
        });
        jPanel1.add(jButton_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelarActionPerformed
        // TODO add your handling code here:
        Cancelar();
    }//GEN-LAST:event_jButton_cancelarActionPerformed

    private void jButton_cancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_cancelarKeyPressed
        // TODO add your handling code here:
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            Cancelar();
        }
    }//GEN-LAST:event_jButton_cancelarKeyPressed

    private void jTextField_TEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_TEKeyPressed
        // TODO add your handling code here:
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            Cancelar();
        }
    }//GEN-LAST:event_jTextField_TEKeyPressed

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
            java.util.logging.Logger.getLogger(Cancelar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cancelar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cancelar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cancelar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cancelar dialog = new Cancelar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_TE;
    // End of variables declaration//GEN-END:variables

    private boolean Reservado(String TE) {
        String cxx = "", historial = "", TE_tipo = "TE_alojamiento", reservado_fecha = "", TE_relacionado = "";
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select CXX, Historial_modificaciones, Reservado_fecha, "
                    + "TE_traslado from servicios where TE_alojamiento = '" + TE + "'");

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cxx = rs.getString("CXX");
                historial = rs.getString("Historial_modificaciones");
                reservado_fecha = rs.getString("Reservado_fecha");
                TE_relacionado = rs.getString("TE_traslado");
                cn.close();
                if (cxx.equals("NO") && reservado_fecha.equals(Util.getFecha_actual())) {//si no es TE reservado el mismo dia
                    Desechar(TE, TE_tipo, TE_relacionado);
                } else {
                    if (cxx.equals("SI") || cxx.equals("SI_aloj")) {
                        JOptionPane.showMessageDialog(null, "No se puede realizar la operación. El comprobante \n"
                                + "ya está cancelado en sistema.");
                    } else {
                        Actualizar_TE(TE, TE_tipo, cxx, historial);
                    }
                }
                return true;
            } else {
                TE_tipo = "TE_traslado";
                pst = cn.prepareStatement("select CXX, Historial_modificaciones, Reservado_fecha, TE_alojamiento from "
                        + "servicios where TE_traslado = '" + TE + "'");
                rs = pst.executeQuery();
                if (rs.next()) {
                    cxx = rs.getString("CXX");
                    historial = rs.getString("Historial_modificaciones");
                    reservado_fecha = rs.getString("Reservado_fecha");
                    TE_relacionado = rs.getString("TE_alojamiento");
                    cn.close();
                    if (cxx.equals("NO") && reservado_fecha.equals(Util.getFecha_actual())) {//si no es TE reservado el mismo dia
                        Desechar(TE, TE_tipo, TE_relacionado);
                    } else {
                        if (cxx.equals("SI") || cxx.equals("SI_trf")) {
                            JOptionPane.showMessageDialog(null, "No se puede realizar la operación. El comprobante \n"
                                    + "ya está cancelado en sistema.");
                        } else {
                            Actualizar_TE(TE, TE_tipo, cxx, historial);
                        }
                    }
                    return true;
                }
            }
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage().toString());
        }
        return false;
    }

    private void Actualizar_TE(String TE, String TE_tipo, String cxx, String historial) {
        int x = JOptionPane.showConfirmDialog(null, "El número de comprobante introducido existe ya en la BD.\n"
                + "Seguro que desea cancelarlo?");
        if (x == JOptionPane.OK_OPTION) {
            String cancelado = cxx, query = "", historial_modificaciones = historial;

//            System.out.println("Dentro de actualizar_TE: ");
//            System.out.println("TE: " + TE);
//            System.out.println("Tipo TE: " + TE_tipo);
//            System.out.println("cxx: " + cxx);
//            System.out.println("");
            if (cxx.equals("NO")) {
                if (TE_tipo.equals("TE_alojamiento")) {
                    cancelado = "SI_aloj";
                    historial_modificaciones += Util.getFecha_actual() + " - CXX TE Hotel - " + Login.user + "\n";
                } else if (TE_tipo.equals("TE_traslado")) {
                    cancelado = "SI_trf";
                    historial_modificaciones += Util.getFecha_actual() + " - CXX TE TRF - " + Login.user + "\n";
                }
            } else if (cxx.equals("SI_aloj") || cxx.equals("SI_trf")) {
                cancelado = "SI";
                historial_modificaciones += Util.getFecha_actual() + " - CXX - " + Login.user + "\n";
            }

            if (TE_tipo.equals("TE_alojamiento")) {
                query = "update Servicios set CXX=?, Historial_modificaciones=? where TE_alojamiento = '" + TE + "'";
            } else if (TE_tipo.equals("TE_traslado")) {
                query = "update Servicios set CXX=?, Historial_modificaciones=? where TE_traslado = '" + TE + "'";
            }

//            System.out.println("Después de condicional - cancelado: " + cancelado);
//            System.out.println("Después de condicional - query: " + query);
//            System.out.println("");
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(query);

                pst.setString(1, cancelado);
                pst.setString(2, historial_modificaciones);
                pst.executeUpdate();
                cn.close();
                JOptionPane.showMessageDialog(null, "Comprobante cancelado.");
                Limpiar();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage().toString());
            }
        }
    }

    private void Cancelar() {
        String TE = jTextField_TE.getText();

        if (TE.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar el campo 'TE'.");
        } else {
            if (!Reservado(TE)) {
                try {
                    Connection cn = Conexion.conectar();
                    PreparedStatement pst = cn.prepareStatement("insert into servicios values (?,?,?,?,?,?,?,?,?,?,?,"
                            + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    pst.setInt(1, 0);
                    pst.setString(2, "");
                    pst.setString(3, "CANCELADO");//decripcion serv
                    pst.setString(4, TE);//TE Hotel
                    pst.setString(5, "");//Hotel
                    pst.setString(6, "");//Destino
                    pst.setString(7, "");//desde
                    pst.setString(8, "");//hasta
                    pst.setString(9, "");//traslado
                    pst.setString(10, "");//TE trf
                    pst.setString(11, Util.getFecha_actual());//fecha reservado
                    pst.setString(12, Login.user);//reservado por
                    pst.setString(13, "");//observaciones serv
                    pst.setString(14, "");//proveedor
                    pst.setString(15, "");//confirmacion
                    pst.setString(16, "");//adultos
                    pst.setString(17, "");//historial modificaciones
                    pst.setString(18, "");//nombre
                    pst.setString(19, "");//menores
                    pst.setString(20, "");//infante
                    pst.setString(21, "");//apellido
                    pst.setDouble(22, 0);//efectivo alojamiento
                    pst.setDouble(23, 0);//efectivo trf
                    pst.setDouble(24, 0);// crédito alojamiento
                    pst.setDouble(25, 0);//crédito trf
                    pst.setString(26, "");//tarjetas crédito
                    pst.setString(27, "SI");

                    pst.executeUpdate();
                    cn.close();
                    JOptionPane.showMessageDialog(null, "Comprobante cancelado.");
                    Limpiar();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage().toString());
                }
            }
        }
    }
    
    private void Desechar(String TE, String TE_tipo, String TE_relacionado){
        int x = 0;
        String TE_aloj = "", TE_trf = "";
        
        if(TE_relacionado.equals("")){
            x = JOptionPane.showConfirmDialog(null,"El ticket fue reservado en el día de hoy. Confirma\n"
                    + "que desea cancelarlo");
            TE_aloj = TE;
        }else{
            x = JOptionPane.showConfirmDialog(null,"El ticket fue reservado en el día de hoy. Si lo cancela, se\n"
                    + "cancelará también el TE" + TE_relacionado + ". Desea cancelar\n"
                    + "de todas maneras?");
            if(TE_tipo.equals("TE_alojamiento")){
                TE_aloj = TE;
                TE_trf = TE_relacionado;
            }else if(TE_tipo.equals("TE_traslado")){
                TE_aloj = TE_relacionado;
                TE_trf = TE;
            }
        }
        
        if(x == JOptionPane.OK_OPTION){
            String query = "";
            if(TE_tipo.equals("TE_alojamiento")){
                query = "update servicios set Id_cliente=?,Descripcion=?,TE_alojamiento=?,Hotel=?,Destino=?,"
                        + "Fecha_inicio=?,Fecha_fin=?,Traslado=?,TE_traslado=?,Observaciones=?,Proveedor_trf=?,"
                        + "No_conf=?,Pax=?,Historial_modificaciones=?,Nombre_cliente=?,Menores=?,Infantes=?,"
                        + "Apellido_cliente=?,Efectivo_hotel=?,Efectivo_trf=?,Credito_hotel=?,Credito_trf=?,"
                        + "Tarjeta_credito=?,CXX=? where TE_alojamiento = '" + TE + "'";
            }else if(TE_tipo.equals("TE_traslado")){
                query = "update servicios set Id_cliente=?,Descripcion=?,TE_alojamiento=?,Hotel=?,Destino=?,"
                        + "Fecha_inicio=?,Fecha_fin=?,Traslado=?,TE_traslado=?,Observaciones=?,Proveedor_trf=?,"
                        + "No_conf=?,Pax=?,Historial_modificaciones=?,Nombre_cliente=?,Menores=?,Infantes=?,"
                        + "Apellido_cliente=?,Efectivo_hotel=?,Efectivo_trf=?,Credito_hotel=?,Credito_trf=?,"
                        + "Tarjeta_credito=?,CXX=? where TE_traslado = '" + TE + "'";
            }
            
            try{
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(query);
                
//                pst.setString(1,"");//id_cliente
//                pst.setString(2,"CANCELADO");//descripcion
//                pst.setString(3,TE_aloj);//TE_alojamiento
//                pst.setString(4,"");//Hotel
//                pst.setString(5,"");//Destino
//                pst.setString(6,"");//Fecha inicio
//                pst.setString(7,"");//Fecha_fin
//                pst.setString(8,"");//Traslado
//                pst.setString(9,TE_trf);//TE trf
//                pst.setString(10,"");//Observaciones
//                pst.setString(11,"");//Proveedor_trf
//                pst.setString(12,"");//Confirmacion
//                pst.setString(13,"");//Pax
//                pst.setString(14,Util.getFecha_actual() + " - CXX - " + Login.user + "\n");//Historial_modificaciones
//                pst.setString(15,"");//nombre
//                pst.setString(16,"");//menores
//                pst.setString(17,"");//infantes
//                pst.setString(18,"");//apellido
//                pst.setDouble(19,0);//efectivo hotel
//                pst.setDouble(20,0);//efectivo trf
//                pst.setDouble(21,0);//credito hotel
//                pst.setDouble(22,0);// credito trf
//                pst.setString(23,"");//tarjeta de credito
//                pst.setString(24,"SI");//cxx
                
                for(int i=1; i<=24; i++){
                    if(i==1 || (i>=4 && i<=8) || (i>=10 && i<=13) || (i>=15 && i<=18) || i==23){
                        pst.setString(i,"");
                    }
                    if(i>=19 && i<=22){
                        pst.setDouble(i,0);
                    }
                    if(i==2){
                        pst.setString(i,"CANCELADO");
                    }
                    if(i==3){
                        pst.setString(i,TE_aloj);
                    }
                    if(i==9){
                        pst.setString(i,TE_trf);
                    }
                    if(i==14){
                        pst.setString(i,Util.getFecha_actual() + " - CXX - " + Login.user + "\n");
                    }
                    if(i==24){
                        pst.setString(i,"SI");
                    }
                }
                
                pst.executeUpdate();
                cn.close();
                JOptionPane.showMessageDialog(null, "Comprobante cancelado.");
                Limpiar();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Error: " + e.getMessage().toString());
            }
        }
    }

    private void Limpiar() {
        jTextField_TE.setText("");
    }
}