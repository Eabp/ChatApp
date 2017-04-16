
package TADCola;

import TADCola.Nodo;

public class ColaLista {
    protected Nodo frente;
    protected Nodo fin;
    private int cantidad;
    
    public ColaLista(){
        frente = null;
        cantidad = 0;
    }
    
    public void insertar(Object elemento){
        Nodo a;
        a = new Nodo(elemento);
        if(colaVacia()){
            frente = a;
        }
        else{
            fin.siguiente = a;
        }
        fin = a;
        cantidad++;
    }
    
    public Object quitar()throws Exception{
        Object aux = null;
        if (!colaVacia()){
            aux = frente.elemento;
            frente = frente.siguiente;
        }
        else{
            throw new Exception("Error: Cola vacia...");   
        }
        return aux;
    }
        

    public void borrarCola(){
            while(frente != null)
                frente = frente.siguiente;
            System.gc();
        }
        
        public Object frenteCola()throws Exception{
            if (colaVacia()){
                throw new Exception("Error, cola vacia");
            }
            return (frente.elemento);
        }
        
        public boolean colaVacia(){
            return (frente == null);
        }
        
        public boolean imprimirCola(){
            Nodo aux = frente;
            if (colaVacia()){
                return false;
            }
            while (aux != null){
                System.out.println(aux.elemento);
                aux = aux.siguiente;
            }
            return true;
            
        }
        
        public int getCantidad(){
            return cantidad;
        }
        
        public Object[] devolverCola(){
            if (colaVacia())
                return null;
            Object[] miColaAux = new Object[this.getCantidad()];
            Nodo aux = frente;
            int i = 0;
            while(aux != null){
                miColaAux[i] = aux.elemento;
                i++;
                aux = aux.siguiente;
            }
            return miColaAux;
        }
}


