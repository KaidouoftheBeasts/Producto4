package beepod.modelo;

public class ListaClientes extends Lista<Cliente>{
    /**
     * constructor
     */
    public ListaClientes() {
        super();
    }

    @Override
    public String toString() {
        return "ListaClientes: " + super.toString();
    }
}
