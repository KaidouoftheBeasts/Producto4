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

    // Método para crear un nuevo cliente
    public void crearCliente(String nombre, String domicilio, String nif, String email, int opcion) {
        try {
            if (opcion == 1) {
                ClienteNormal clienteNormal = new ClienteNormal(nombre, domicilio, nif, email);
                datos.getListaClientes().addElemento(clienteNormal);
                System.out.println("Cliente Standar añadido.\n" + clienteNormal);
            } else {
                ClientePremium clientePremium = new ClientePremium(nombre, domicilio, nif, email);
                datos.getListaClientes().addElemento(clientePremium);
                System.out.println("Cliente premiun añadido.\n" + clientePremium);
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear el cliente: " + e.getMessage());
        }
    }

    // Método para listar todos los clientes
    public void listarTodosClientes() {
        try {
            for (Cliente cliente : datos.getListaClientes().getLista()) {
                System.out.println(cliente);
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar los clientes: " + e.getMessage());
        }
    }

    // Método para listar los clientes normales
    public void listarClientesNormal() {
        try {
            for (Cliente cliente : datos.getListaClientes().getLista()) {
                if (cliente.tipoCliente().equals("Estandar"))
                    System.out.println(cliente);
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar los clientes normales: " + e.getMessage());
        }
    }

    // Método para listar los clientes premium
    public void listarClientesPremium() {
        try {
            for (Cliente cliente : datos.getListaClientes().getLista()) {
                if (cliente.tipoCliente().equals("Premium"))
                    System.out.println(cliente);
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar los clientes premium: " + e.getMessage());
        }
    }

    // Método para crear un nuevo artículo
    public void crearArticulo(String codigo, String descripcion, float precioVenta, float gastosEnvio, long tiempoPreparacion) {
        try {
            Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
            datos.getListaArticulos().addElemento(articulo);
            System.out.println("Articulo creado:\n" + articulo);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear el artículo: " + e.getMessage());
        }
    }

    // Método para listar todos los artículos
    public void listarArticulos() {
        try {
            for (Articulo articulo : datos.getListaArticulos().getLista()) {
                System.out.println(articulo);
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar los artículos: " + e.getMessage());
        }
    }
    // Método para crear un nuevo pedido
    public void crearPedido(String email) {
        try {
            Cliente cliente = null;
            // Comprobar si el cliente existe
            for (Cliente c : datos.getListaClientes().getLista()) {
                if (c.getEmail().equals(email)) {
                    cliente = c;
                    break;
                }
            }
            // Si no se encuentra el cliente, crear uno nuevo
            if (cliente == null) {
                System.out.println("El cliente con correo electrónico " + email + " no existe.");
                System.out.println("Creando nuevo cliente...");
                GestionClientes gestionClientes = new GestionClientes();
                gestionClientes.datosCliente(this);
            }

            // Si el cliente no es nulo, listar los artículos disponibles
            if (cliente != null) {
                // Listar los artículos disponibles
                System.out.println("Artículos disponibles: ");
                listarArticulos();

                // Pedir el código del artículo
                System.out.println("Introduce el código del artículo (o escribe 'exit' para salir): ");
                String codigo = s.nextLine();

                // Comprobar si el artículo existe
                Articulo articulo = null;
                boolean encontrado = false;
                while (!encontrado && !codigo.equals("exit")) {
                    for (Articulo a : datos.getListaArticulos().getArrayList()) {
                        if (a.getCodigo().equals(codigo)) {
                            articulo = a;
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("El artículo con código " + codigo + " no existe.");
                        System.out.println("Introduce el código del artículo de nuevo (o escribe 'exit' para salir): ");
                        codigo = s.nextLine();
                    }
                }

                // Si no se encontró el artículo y se escribió "exit", salir del método
                if (!encontrado && codigo.equals("exit")) {
                    return;
                }

                // Guardar el precio original del envío
                float precioOriginal = articulo.getGastosEnvio();

                // Aplicar el descuento si el cliente es premium
                if (cliente instanceof ClientePremium) {
                    ClientePremium clientePremium = (ClientePremium) cliente;
                    float descuento = clientePremium.getDescuento();
                    float precioConDescuento = articulo.getGastosEnvio() * (1 - descuento);
                    articulo.setGastosEnvio(precioConDescuento);
                }

                // Pedir la cantidad del artículo
                System.out.println("Introduce la cantidad (debe ser un número entero): ");
                int cantidad = 0;
                boolean cantidadValida = false;
                while (!cantidadValida) {
                    if (s.hasNextInt()) {
                        cantidad = s.nextInt();
                        cantidadValida = true;
                    } else {
                        System.out.println("La cantidad debe ser un número entero. Introduce la cantidad de nuevo: ");
                        s.nextLine(); // Consumir la entrada no válida
                    }
                }
                s.nextLine(); // Consumir el salto de línea pendiente

                // Obtener el último número de pedido y aumentarlo en 1
                int numPedido = 1;
                if (!datos.getListaPedidos().getArrayList().isEmpty()) {
                    Pedido ultimoPedido = datos.getListaPedidos().getArrayList().get(datos.getListaPedidos().getArrayList().size() - 1);
                    numPedido = ultimoPedido.getNumPedido() + 1;
                }
                // Calcular total
                float total = articulo.getPrecioVenta() * cantidad;

                // Crear el pedido
                LocalDateTime fecha = LocalDateTime.now();
                Pedido pedido = new Pedido(cliente, articulo, cantidad);

                pedido.setTotal(total);

                // Añadir el pedido a la lista de pedidos
                datos.getListaPedidos().addElemento(pedido);

                System.out.println("Pedido añadido correctamente.");
                System.out.println(pedido.toString());

                // Restaurar el precio original del envío después de crear el pedido
                articulo.setGastosEnvio(precioOriginal);
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }
}
