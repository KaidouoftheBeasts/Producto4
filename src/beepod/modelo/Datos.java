package beepod.modelo;

public class Datos {

    //private ListaClientesNormal listaClientes;
    //private ListaClientesPremium listaClientesPremium;
    private ListaArticulos listaArticulos;
    private ListaClientes listaClientes;
    private ListaPedidos listaPedidos;

    public Datos() {
        //listaClientesNormal = new ListaClientesNormal();
        //listaClientesPremium = new ListaClientesPremium();
        listaClientes = new ListaClientes();
        listaArticulos = new ListaArticulos();
        listaPedidos = new ListaPedidos();
    }

   // public ListaClientesNormal getListaClientesNormal() {
      //  return listaClientesNormal;
   // }

  //  public void setListaClientesNormal(ListaClientesNormal listaClientesNormal) {
   //     this.listaClientesNormal = listaClientesNormal;
  //  }

  //  public ListaClientesPremium getListaClientesPremium() {
   //     return listaClientesPremium;
  //  }

    public ListaClientes getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    // public void setListaClientesPremium(ListaClientesPremium listaClientesPremium) {
    //    this.listaClientesPremium = listaClientesPremium;
  //  }

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
