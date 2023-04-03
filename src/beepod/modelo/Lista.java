package beepod.modelo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase genérica
 * @param <T>
 */

public class Lista <T> {
    Scanner input = new Scanner(System.in);
    protected ArrayList<T> lista;

    /**
     * Constructor
     */
    public Lista() {
        lista = new ArrayList<>();
    }

    /**
     * Getters y Setters
     */
    public ArrayList<T> getLista() {
        return lista;
    }

    public void setLista(ArrayList<T> lista) {
        this.lista = lista;
    }

    /**
     * Metodo agregar elementos a la lista
     * @param t
     */
    public void addElemento(T t){
        lista.add(t);
    }

    public void borrarElemento(T t){
        lista.remove(t);
    }

    /**
     * Metodo para el tamaño de la lista
     * @return
     */
    public int getSize(){
        return lista.size();
    }

    /**
     * Obtener un elemento en concreto del array list.
     * @param posicion
     * @return
     */
    public T getAt(int posicion){
        return lista.get(posicion);
    }

    /**
     * Nos devuleve un arrayList
     * @return
     */
    public ArrayList<T> getArrayList(){
        ArrayList<T> arrayList = new ArrayList<>(lista);
        return arrayList;
    }

    @Override
    public String toString() {
        return "Lista{" +
                "lista: " + lista +
                '}';
    }
}
