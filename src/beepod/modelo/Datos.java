package beepod.modelo;

public class Datos {

    private ListaClientesNormal listaClientesNormal;
    private ListaClientesPremium listaClientesPremiun;
    private ListaArticulos listaArticulos;
    private ListaPedidos listaPedidos;
    private Articulo articulo;


    /**
     * constructor
     * @param listaClientesNormal
     * @param listaClientesPremium
     * @param listaArticulos
     * @param listaPedidos
     */
    public Datos(ListaClientesNormal listaClientesNormal, ListaClientesPremium listaClientesPremium, ListaArticulos listaArticulos, ListaPedidos listaPedidos, Articulo articulo){
        this.listaClientesNormal = listaClientesNormal;
        this.listaClientesPremiun = listaClientesPremium;
        this.listaArticulos = listaArticulos;
        this.listaPedidos = listaPedidos;
        this.articulo = articulo;
    }
    public Datos(){
    }

    /**
     * Getters y Setters
     * @return
     */
    public ListaClientesNormal getListaClientesNormal() {
        return listaClientesNormal;
    }

    public void setListaClientesNormal(ListaClientesNormal listaClientesNormal) {
        this.listaClientesNormal = listaClientesNormal;
    }
    public ListaClientesPremium getListaClientesPremiun() {
        return listaClientesPremiun;
    }

    public void setListaClientesPremiun(ListaClientesPremium listaClientesPremiun) {
        this.listaClientesNormal = listaClientesNormal;
    }


    public ListaArticulos getListaArticulos() { return listaArticulos; }

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
