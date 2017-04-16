/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TADCola;

/**
 *
 * @author laboratorio
 */
public class PruebasCola {

    public static void main(String[] args) {
        ColaLista miCola = new ColaLista();
        miCola.insertar("hola");
        miCola.insertar("Adios");
        miCola.insertar("como te va");
        miCola.insertar("Adios");
        System.out.println(miCola.imprimirCola());
        miCola.insertar("otro elemento mas");
        System.out.println(miCola.imprimirCola());
        Object[] aux;
        aux = miCola.devolverCola();
        for(int i = 0; i < miCola.getCantidad(); i++)
            System.out.println(aux[i]);
    }
    
}
