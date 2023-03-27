package beepod.controlador;


import beepod.modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {
    Scanner s = new Scanner(System.in);
    private Datos datos;
    private ClienteNormal clienteNormal = new ClienteNormal();
    private ClientePremium clientePremium = new ClientePremium();
    private ArrayList<Articulo> listaArticulos;
    private ArrayList<ClienteNormal> listaClientesNormal = new ArrayList<>();
    private ArrayList<ClientePremium> listaClientesPremium = new ArrayList<>();


    private ArrayList<Pedido> ListaPedidos;


    protected ListaClientesNormal clientesNormal = new ListaClientesNormal(listaClientesNormal);
    protected ListaClientesPremium clientesPremium = new ListaClientesPremium(listaClientesPremium);
    protected ListaArticulos articulos = new ListaArticulos(listaArticulos);
    protected ListaPedidos pedidos = new ListaPedidos(ListaPedidos);


    public Controlador() {
        datos = new Datos(clientesNormal,clientesPremium,articulos,pedidos);
    }

    public void crearClientes(){
        System.out.println("Introduce el nombre: ");
        String nombre = s.nextLine();
        System.out.println("Introduce el domicilio: ");
        String domicilio = s.nextLine();
        System.out.println("Introduce el nif: ");
        String nif = s.nextLine();
        System.out.println("Introduce el email: ");
        String email = s.nextLine();
        System.out.println("Introduce el tipo de cliente: '1' para cliente Normal o '2' para Premium");
        int opcion = s.nextInt();
        if (opcion == 1){
            clienteNormal = new ClienteNormal(nombre, domicilio, nif, email);
            datos.setListaClientesNormal(clientesNormal);
            //listaClientesNormal.add(clienteNormal);

            System.out.println("Cliente Standar añadido "+ clienteNormal);
        }else{
            s.nextLine();
            System.out.println("Introduce el importe de la cuota: ");
            float cuota = s.nextFloat();
            System.out.println("Introduce el descuento: ");
            float descuento = s.nextFloat();
            clientePremium = new ClientePremium(nombre,domicilio,nif, email, cuota, descuento);
            datos.setListaClientesPremiun(clientesPremium);
            //listaClientesPremium.add(clientePremium);
            System.out.println("Cliente premiun añadido "+ clientePremium);
        }
        s.nextLine();
    }
    public void listarClientesNormal(){
        for (ClienteNormal clienteNormal: listaClientesNormal){//ver para usar datos.
            System.out.println(clienteNormal);
        }
    }


}
