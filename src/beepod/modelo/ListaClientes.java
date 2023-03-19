package beepod.modelo;

import java.util.ArrayList;

public class ListaClientes extends Lista<Cliente>{
    /**
     * constructor
     * @param lista
     */
    public ListaClientes(ArrayList<Cliente> lista) {
        super(lista);
    }

    @Override
    public String toString() {
        return "ListaClientes{} " + super.toString();
    }
}
