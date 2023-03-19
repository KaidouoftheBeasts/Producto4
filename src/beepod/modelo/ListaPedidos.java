package beepod.modelo;

import java.util.ArrayList;

public class ListaPedidos extends Lista<Pedido>{
    /**
     * constructor
     * @param lista
     */
    public ListaPedidos(ArrayList<Pedido> lista) {
        super(lista);
    }

    @Override
    public String toString() {
        return "ListaPedidos{} " + super.toString();
    }
}
