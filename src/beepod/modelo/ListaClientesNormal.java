package beepod.modelo;

import java.util.ArrayList;

public class ListaClientesNormal extends Lista<ClienteNormal>{
    /**
     * constructor
     * @param lista
     */
    public ListaClientesNormal(ArrayList<ClienteNormal> lista) {
        super(lista);
    }

    @Override
    public String toString() {
        return "ListaClientesNormal{} " + super.toString();
    }
}
