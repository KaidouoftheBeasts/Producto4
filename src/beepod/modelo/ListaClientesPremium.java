package beepod.modelo;

import java.util.ArrayList;

public class ListaClientesPremium extends Lista<ClientePremium>{
    /**
     * constructor
     * @param lista
     */
    public ListaClientesPremium(ArrayList<ClientePremium> lista) {
        super();
    }

    @Override
    public String toString() {
        return "ListaClientesPremiun{} " + super.toString();
    }
}
