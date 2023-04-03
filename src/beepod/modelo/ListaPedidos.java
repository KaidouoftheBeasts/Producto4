package beepod.modelo;

public class ListaPedidos extends Lista<Pedido>{
    /**
     * constructor
     */
    public ListaPedidos() {
        super();
    }

    @Override
    public String toString() {
        return "ListaPedidos: " + super.toString();
    }
}
