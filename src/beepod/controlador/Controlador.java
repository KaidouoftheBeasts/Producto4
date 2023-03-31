package beepod.controlador;


import beepod.modelo.*;
import beepod.vista.GestionClientes;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Controlador {
    GestionClientes gestionClientes = new GestionClientes();
    Scanner s = new Scanner(System.in);
    private Datos datos;

    public Controlador() {
        datos = new Datos();
    }

    public void crearCliente(String nombre, String domicilio, String nif, String email, int opcion){
        if (opcion == 1){
            ClienteNormal clienteNormal = new ClienteNormal(nombre, domicilio, nif, email);
            datos.getListaClientes().addElemento(clienteNormal);
            System.out.println("Cliente Standar añadido "+ clienteNormal);
        }else{
            ClientePremium clientePremium = new ClientePremium(nombre,domicilio,nif, email);
            datos.getListaClientes().addElemento(clientePremium);
            System.out.println("Cliente premiun añadido "+ clientePremium);
        }
    }
    public void listarTodosClientes(){
        for (Cliente cliente: datos.getListaClientes().getLista()) {
            System.out.println(cliente);
        }
    }
    public void listarClientesNormal(){
        for (Cliente cliente: datos.getListaClientes().getLista()) {
            if (cliente.tipoCliente().equals("Estandar"))
                System.out.println(cliente);
        }
    }
    public void listarClientesPremium(){
        for (Cliente cliente: datos.getListaClientes().getLista()) {
            if (cliente.tipoCliente().equals("Premium"))
                System.out.println(cliente);
        }
    }
    public void crearArticulo(String codigo, String descripcion, float precioVenta, float gastosEnvio, long tiempoPreparacion){
        Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
        datos.getListaArticulos().addElemento(articulo);
        System.out.println("Articulo creado!!"+articulo);
    }
    public void listarArticulos(){
        for (Articulo articulo : datos.getListaArticulos().getLista()){//ver para usar datos.
            System.out.println(articulo);
        }
    }

    public void crearPedido(String email) {
        Cliente cliente = null;
        // Comprobar si el cliente existe
        for (Cliente c : datos.getListaClientes().getLista()) {
            if (c.getEmail().equals(email)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("El cliente con correo electrónico " + email + " no existe.");
            System.out.println("Creando nuevo cliente...");
            GestionClientes gestionClientes = new GestionClientes();
            gestionClientes.datosCliente(this);
        }

        // Si el cliente no es nulo, listar los artículos disponibles
        if (cliente != null) {
            // Listar los artículos disponibles
            System.out.println("Artículos disponibles:");
            listarArticulos();

            // Pedir el código del artículo
            System.out.println("Introduce el código del artículo:");
            String codigo = s.nextLine();

            // Comprobar si el artículo existe
            while (datos.getListaArticulos().getArrayList() == null) {
                System.out.println("El artículo con código " + codigo + " no existe.");
                System.out.println("Introduce el código del artículo de nuevo:");
                codigo = s.nextLine();
            }

            // Pedir la cantidad del artículo
            System.out.println("Introduce la cantidad:");
            int cantidad = s.nextInt();

            // Crear el pedido
            LocalDateTime fecha = LocalDateTime.now();
            Pedido pedido = new Pedido(numPedido, cliente, articulo, cantidad, fecha, enviado);
            pedido.setCliente(cliente);
            Articulo articulo = datos.getListaArticulos(codigo);
            pedido.addItem(articulo, cantidad);

            // Añadir el pedido a la lista de pedidos
            datos.getListaPedidos().addElemento(pedido);

            System.out.println("Pedido añadido correctamente.");
        }
    }
}
