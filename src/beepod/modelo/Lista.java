package beepod.modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Lista <T> {
    Scanner input = new Scanner(System.in);
    protected ArrayList<T> lista;

    /**
     * Constructor
     * @param lista
     */
    public Lista(ArrayList<T> lista) {
        this.lista = lista;
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

    public void borrarElemento(){
        int i;
        int index;
        /**
         * Lista de elementos disponibles
         */
        System.out.println("Seleccionar el número de elemento a borrar");
        for ( i = 0 ; i < lista.size(); i++){
        }
        index = input.nextInt();
        lista.remove(index);
    }

    /**
     * Metodo para el tamaño de la lista
     * @return
     */
    public int getSize(){
        int arrayListSize = lista.size();
        return arrayListSize;
    }

    /**
     * Obtener un elemento en concreto del array list.
     * @param posicion
     * @return
     */
    public T getAt(int posicion){
        T elemento = lista.get(posicion);
        return elemento;
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
                "lista=" + lista +
                '}';
    }
}
