package beepod.controlador;


import beepod.modelo.*;

import java.util.ArrayList;

public class Controlador {
    private Datos datos;
    private ArrayList<Articulo> ListaArticulos;
    private ArrayList<Cliente> ListaClientes;
    private ArrayList<Pedido> ListaPedidos;

    protected ListaArticulos articulos = new ListaArticulos(ListaArticulos);
    protected ListaClientes clientes = new ListaClientes(ListaClientes);
    protected ListaPedidos pedidos = new ListaPedidos(ListaPedidos);

    public Controlador() {
        datos = new Datos(clientes,articulos,pedidos);
    }


}
