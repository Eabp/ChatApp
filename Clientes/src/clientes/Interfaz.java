package clientes;

import TADCola.ColaLista;
import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Interfaz extends javax.swing.JFrame {
    
    String nameUser = "Equipo #3";
    private Socket conexion;
    private DataOutputStream output;
    private Receptor receptor;
    private String msj;
    private boolean status;
    private ColaLista miHistorial;
    private DefaultListModel dlm;
    Object[] aux;
    
    public Interfaz() {
        try {
            initComponents();
            dlm = new DefaultListModel();
            jList1.setModel(dlm);
            msj = "En linea";
            miHistorial = new ColaLista();
            conexion = new Socket(JOptionPane.showInputDialog(null,
                                    "Escriba el nombre del equipo a conectar",
                                    "Conexion", JOptionPane.INFORMATION_MESSAGE), 5000);
            output = new DataOutputStream(conexion.getOutputStream());
            receptor = new Receptor(conexion,miHistorial,"Usuario 1",dlm);
        } catch (IOException ex) {
            System.out.println(ex);
        }catch (NullPointerException ex){
            System.out.println("Error en el nombre del equipo");
        }
        receptor.start();
    }
    
    public Receptor getReceptor(){
        return receptor;
        
    }
    
    public void cerrarConexiones(){
        try {
            conexion.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void setNameUser(String name){
        nameUser = name;
    }
    
    public String getNameUser(){
        return nameUser;
    }
    
    private String crearHistoria(String texto){
        String historia;
        historia = getNameUser() + " :     " + texto;
        return historia;
    }
    
    public void llenarHistorial(String msj){
        miHistorial.insertar(crearHistoria(msj));
    }
    
    public ColaLista getHistorial(){
        return miHistorial;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Messenger");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 59, 294, 282));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 347, 294, 32));

        jButton1.setText("Enviar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 347, 111, 32));

        jButton2.setText("Vaciar Chat");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 59, 111, 35));

        jButton3.setText("Mostrar Historial");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 100, -1, 31));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Grupo #3");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 423, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clientes/fff.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(!jTextField1.getText().trim().equals("")){
            jTextField1.setBackground(Color.WHITE);
            msj = jTextField1.getText().toString();
            llenarHistorial(msj);
            dlm.addElement(getNameUser()+":   "+msj);
            try {
                output.writeUTF(msj);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            jTextField1.setText("");
            jTextField1.grabFocus();
        } else{
            jTextField1.setBackground(Color.YELLOW);
        } 
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        aux = miHistorial.devolverCola();
        dlm.removeAllElements();
        for(int i = 0; i < miHistorial.getCantidad(); i++){
            dlm.addElement(aux[i]);
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        dlm.removeAllElements();
    }//GEN-LAST:event_jButton2MouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.cerrarConexiones();
        receptor.setEstado(false);
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
