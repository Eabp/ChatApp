package servidor;

import TADCola.*;
import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.DefaultListModel;

public class Interfaz extends javax.swing.JFrame {
    
    private String nameUser;
    private ServerSocket server;
    private Socket socket;
    private DataOutputStream output;
    private Receptor miReceptor;
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
            nameUser = "Usuario 1";
            miHistorial = new ColaLista();
            msj = "Conexion Establecida";
            server = new ServerSocket(5000);
            socket = server.accept();
            miReceptor = new Receptor(socket,miHistorial,"Equipo #3",dlm);
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex);
        }
        miReceptor.start();
    }
    
    public Receptor getReceptor(){
        return miReceptor;
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
    
    
    public void cerrarConexiones(){
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
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
        setBackground(new java.awt.Color(102, 255, 255));
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
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 347, 123, 32));

        jButton2.setText("Vaciar Chat");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 59, 123, 35));

        jButton3.setText("Mostrar Historial");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 105, 123, 33));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Usuario 1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 426, 30));

        jLabel2.setBackground(new java.awt.Color(31, 242, 242));
        jLabel2.setForeground(new java.awt.Color(51, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/servidor/Sin título.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 400));

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
        miReceptor.setEstado(false);
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed

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
