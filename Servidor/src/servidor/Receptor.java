
package servidor;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import TADCola.ColaLista;
import javax.swing.DefaultListModel;


public class Receptor extends Thread {
    private String nameUser;
    Socket cliente;
    private String msj;
    private DataInputStream input;
    private boolean estado;
    private ColaLista history;
    private DefaultListModel dlmAux;
    
    public Receptor(Socket s, ColaLista history, String name, DefaultListModel dlm){
        dlmAux = dlm;
        this.history = history;
        nameUser = name;
        try {
            cliente = s;
            input = new DataInputStream(cliente.getInputStream());
            estado = true;
        } catch (IOException ex) {
            System.out.println(ex);
        }
        msj = "vacio";
    }
    
    @Override
    public void run(){
        try {
            while(estado){
            msj = input.readUTF();
            llenarHistorial(msj);
            dlmAux.addElement(getNameUser()+":   "+msj);
            }
            cliente.close();
            this.finalize();
        } catch (IOException ex) {
           System.out.println("El cliente ha finalizado su sesion");
        } catch (Throwable ex) {
           System.out.println(ex);
        }
    }
    
    public void setEstado(boolean state){
        estado = state;
    }
    
    public boolean getEstado(){
        return estado;
    }
    
    public void setNameUser(String name){
        nameUser = name;
    }
    
    public String getNameUser(){
        return nameUser;
    }
    
    private String crearHistoria(String texto){
        String historia;
        historia = getNameUser() + " : " + texto;
        return historia;
    }
    
    public void llenarHistorial(String msj){
        history.insertar(crearHistoria(msj));
    }
}
