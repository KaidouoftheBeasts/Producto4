package beepod.modelo;

public class ListaArticulos extends Lista<Articulo>{
    /**
     * constructor
     */
    public ListaArticulos() {
        super();
    }

    @Override
    public String toString() {
        return "ListaArticulos: " + super.toString();
    }
}
