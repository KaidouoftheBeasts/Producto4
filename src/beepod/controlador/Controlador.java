package beepod.controlador;


import beepod.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {
    Scanner s = new Scanner(System.in);
    private Datos datos;
    private ClienteNormal clienteNormal = new ClienteNormal();
    private ClientePremium clientePremium = new ClientePremium();
    private ArrayList<Articulo> listaArticulos = new ArrayList<>();
    private ArrayList<ClienteNormal> listaClientesNormal = new ArrayList<>();
    private ArrayList<ClientePremium> listaClientesPremium = new ArrayList<>();

    private Articulo articulo;


    private ArrayList<Pedido> ListaPedidos;

    protected ListaClientesNormal clientesNormal = new ListaClientesNormal(listaClientesNormal);
    protected ListaClientesPremium clientesPremium = new ListaClientesPremium(listaClientesPremium);
    protected ListaArticulos articulos = new ListaArticulos(listaArticulos);
    protected ListaPedidos pedidos = new ListaPedidos(ListaPedidos);


    public Controlador() {
        datos = new Datos(clientesNormal,clientesPremium,articulos,pedidos, articulo);
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
        boolean salir = false;
        char opcio;
        do{
            System.out.println("Tipos disponibbles de Cliente: '1' para cliente Standar o '2' para Premium");
            opcio = pedirOpcion();

            switch (opcio){
                case '1' :
                    crearClienteStandar(nombre, domicilio, nif, email);
                    salir = true;
                    break;
                case '2' :
                    crearClientePremium(nombre, domicilio, nif, email);
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta!!");
            }
        }while (!salir);
    }
    private char pedirOpcion() {
        String resp;
        System.out.println("Elige la opcion ");
        resp = s.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

    /**
     * Metodo para crear tipo de cliente Standar
     * @param nombre
     * @param domicilio
     * @param nif
     * @param email
     */
    public void crearClienteStandar(String nombre, String domicilio, String nif, String email){
        clienteNormal = new ClienteNormal(nombre, domicilio, nif, email);
        datos.getListaClientesNormal().addElemento(clienteNormal);
        System.out.println("Añadido "+ clienteNormal);
    }

    /**
     * Metodo para crear tipo de cliente Premium contemplando Excepción del tipo de dato descuento.
     * @param nombre
     * @param domicilio
     * @param nif
     * @param email
     */
    public void crearClientePremium(String nombre, String domicilio, String nif, String email){
        System.out.println("La cuota anual para este cliente son 30€");
        float cuota = 30;// valor fijo para este cliente
        boolean entradaValida = false;
        while (!entradaValida) {
            try {

                System.out.println("Introduce el descuento: ");
                float descuento = Float.parseFloat(s.nextLine());
                //float descuento = s.nextFloat();
                clientePremium = new ClientePremium(nombre,domicilio,nif, email, cuota, descuento);
                datos.getListaClientesPremiun().addElemento(clientePremium);
                System.out.println("Añadido "+ clientePremium);
                //s.nextLine();
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }

    public void listarTodosClientes(){
        listarClientesNormal();
        listarClientesPremium();
    }
    public void listarClientesNormal(){
        for (ClienteNormal clienteNormal: datos.getListaClientesNormal().getLista()){
            System.out.println(clienteNormal);
        }
    }
    public void listarClientesPremium(){
        for (ClientePremium clientePremium: datos.getListaClientesPremiun().getLista()){
            System.out.println(clientePremium);
        }
    }
    public void crearArticulo(){
        System.out.println("Introduce el codigo: ");
        String codigo = s.nextLine();
        System.out.println("Introduce la descripcion: ");
        String descripcion = s.nextLine();
        System.out.println("Introduce el precio de venta: ");
        float precioVenta = s.nextFloat();
        System.out.println("Introduce los gastos de envio: ");
        float gastosEnvio = s.nextFloat();
        System.out.println("Introduce el tiempo de preparación");
        long tiempo = s.nextLong();
        articulo = new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempo);
        datos.getListaArticulos().addElemento(articulo);// de las dos maneras se añaden
        s.nextLine();
        System.out.println("Articulo creado!!"+articulo);
    }
    public void listarArticulos(){
        for (Articulo articulo : datos.getListaArticulos().getLista()){
            System.out.println(articulo);
        }
    }
}
