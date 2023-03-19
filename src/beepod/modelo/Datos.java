package beepod.modelo;

public class Datos {
    private ListaClientes listaClientes;
    private ListaArticulos listaArticulos;
    private ListaPedidos listaPedidos;

    /**
     * constructor
     * @param listaClientes
     * @param listaArticulos
     * @param listaPedidos
     */
    public Datos(ListaClientes listaClientes, ListaArticulos listaArticulos,ListaPedidos listaPedidos){
        this.listaClientes = listaClientes;
        this.listaArticulos = listaArticulos;
        this.listaPedidos = listaPedidos;
    }

    /**
     * Getters y Setters
     * @return
     */
    public ListaClientes getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ListaArticulos getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(ListaArticulos listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    public ListaPedidos getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(ListaPedidos listaPedidos) {
        this.listaPedidos = listaPedidos;
    }


}
