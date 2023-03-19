package beepod.controlador;


import beepod.modelo.*;

import java.util.ArrayList;

public class Controlador {
    private Datos datos;
    private ArrayList<Articulo> ListaArticulos;
    private ArrayList<ClienteNormal> ListaClientesNormal;
    private ArrayList<ClientePremium> ListaClientesPremium;


    private ArrayList<Pedido> ListaPedidos;


    protected ListaClientesNormal clientesNormal = new ListaClientesNormal(ListaClientesNormal);

    protected ListaClientesPremium clientesPremium = new ListaClientesPremium(ListaClientesPremium);

    protected ListaArticulos articulos = new ListaArticulos(ListaArticulos);
    protected ListaPedidos pedidos = new ListaPedidos(ListaPedidos);


    public Controlador() {
        datos = new Datos(clientesNormal,clientesPremium,articulos,pedidos);
    }


}
