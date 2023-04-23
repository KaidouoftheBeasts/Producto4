package beepod.controlador;

import beepod.dao.*;
import beepod.dao.factory.*;
import beepod.modelo.*;
import beepod.vista.GestionClientes;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Controlador {

    GestionClientes gestionClientes = new GestionClientes();
    Scanner s = new Scanner(System.in);
    private Datos datos;
    private Conexion con;

    public Controlador() {
        datos = new Datos();
    }

//    // Método para crear un nuevo cliente
//    public void crearCliente1(String nombre, String domicilio, String nif, String email, int opcion) {
//        try {
//            if (opcion == 1) {
//                ClienteNormal clienteNormal = new ClienteNormal(nombre, domicilio, nif, email);
//                datos.getListaClientes().addElemento(clienteNormal);
//                System.out.println("Cliente Standar añadido.\n" + clienteNormal);
//            } else {
//                ClientePremium clientePremium = new ClientePremium(nombre, domicilio, nif, email);
//                datos.getListaClientes().addElemento(clientePremium);
//                System.out.println("Cliente premiun añadido.\n" + clientePremium);
//            }
//        } catch (Exception e) {
//            System.out.println("Ha ocurrido un error al crear el cliente: " + e.getMessage());
//        }
//    }
//
//    // Método para listar todos los clientes
//    public void listarTodosClientes1() {
//        try {
//            for (Cliente cliente : datos.getListaClientes().getLista()) {
//                System.out.println(cliente);
//            }
//        } catch (Exception e) {
//            System.out.println("Ha ocurrido un error al listar los clientes: " + e.getMessage());
//        }
//    }
//
//    // Método para listar los clientes normales
//    public void listarClientesNormal1() {
//        try {
//            for (Cliente cliente : datos.getListaClientes().getLista()) {
//                if (cliente.tipoCliente().equals("Estandar"))
//                    System.out.println(cliente);
//            }
//        } catch (Exception e) {
//            System.out.println("Ha ocurrido un error al listar los clientes normales: " + e.getMessage());
//        }
//    }
//
//    // Método para listar los clientes premium
//    public void listarClientesPremium1() {
//        try {
//            for (Cliente cliente : datos.getListaClientes().getLista()) {
//                if (cliente.tipoCliente().equals("Premium"))
//                    System.out.println(cliente);
//            }
//        } catch (Exception e) {
//            System.out.println("Ha ocurrido un error al listar los clientes premium: " + e.getMessage());
//        }
//    }
//
//    // Método para crear un nuevo artículo
//    public void crearArticulo1(String codigo, String descripcion, float precioVenta, float gastosEnvio, long tiempoPreparacion) {
//        try {
//            Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
//            datos.getListaArticulos().addElemento(articulo);
//            System.out.println("Articulo creado:\n" + articulo);
//        } catch (Exception e) {
//            System.out.println("Ha ocurrido un error al crear el artículo: " + e.getMessage());
//        }
//    }
//
//    // Método para listar todos los artículos
//    public void listarArticulos1() {
//        try {
//            for (Articulo articulo : datos.getListaArticulos().getLista()) {
//                System.out.println(articulo);
//            }
//        } catch (Exception e) {
//            System.out.println("Ha ocurrido un error al listar los artículos: " + e.getMessage());
//        }
//    }
//    // Método para crear un nuevo pedido
//    public void crearPedido1(String email) {
//        try {
//            Cliente cliente = null;
//            // Comprobar si el cliente existe
//            for (Cliente c : datos.getListaClientes().getLista()) {
//                if (c.getEmail().equals(email)) {
//                    cliente = c;
//                    break;
//                }
//            }
//            // Si no se encuentra el cliente, crear uno nuevo
//            if (cliente == null) {
//                System.out.println("El cliente con correo electrónico " + email + " no existe.");
//                System.out.println("Creando nuevo cliente...");
//                GestionClientes gestionClientes = new GestionClientes();
//                gestionClientes.datosCliente(this);
//            }
//
//            // Si el cliente no es nulo, listar los artículos disponibles
//            if (cliente != null) {
//                // Listar los artículos disponibles
//                System.out.println("Artículos disponibles: ");
//                listarArticulos();
//
//                // Pedir el código del artículo
//                System.out.println("Introduce el código del artículo (o escribe 'exit' para salir): ");
//                String codigo = s.nextLine();
//
//                // Comprobar si el artículo existe
//                Articulo articulo = null;
//                boolean encontrado = false;
//                while (!encontrado && !codigo.equals("exit")) {
//                    for (Articulo a : datos.getListaArticulos().getArrayList()) {
//                        if (a.getCodigo().equals(codigo)) {
//                            articulo = a;
//                            encontrado = true;
//                            break;
//                        }
//                    }
//                    if (!encontrado) {
//                        System.out.println("El artículo con código " + codigo + " no existe.");
//                        System.out.println("Introduce el código del artículo de nuevo (o escribe 'exit' para salir): ");
//                        codigo = s.nextLine();
//                    }
//                }
//
//                // Si no se encontró el artículo y se escribió "exit", salir del método
//                if (!encontrado && codigo.equals("exit")) {
//                    return;
//                }
//
//                float precioConDescuento = articulo.getGastosEnvio() * (1 - cliente.descuentoEnv());
//
//                // Pedir la cantidad del artículo
//                System.out.println("Introduce la cantidad (debe ser un número entero): ");
//                int cantidad = 0;
//                boolean cantidadValida = false;
//                while (!cantidadValida) {
//                    if (s.hasNextInt()) {
//                        cantidad = s.nextInt();
//                        cantidadValida = true;
//                    } else {
//                        System.out.println("La cantidad debe ser un número entero. Introduce la cantidad de nuevo: ");
//                        s.nextLine(); // Consumir la entrada no válida
//                    }
//                }
//                s.nextLine(); // Consumir el salto de línea pendiente
//
//                // Obtener el último número de pedido y aumentarlo en 1
//                int numPedido = 1;
//                if (!datos.getListaPedidos().getArrayList().isEmpty()) {
//                    Pedido ultimoPedido = datos.getListaPedidos().getArrayList().get(datos.getListaPedidos().getArrayList().size() - 1);
//                    numPedido = ultimoPedido.getNumPedido() + 1;
//                }
//                // Calcular total
//                float total = articulo.getPrecioVenta() * cantidad;
//
//                // Crear el pedido
//                LocalDateTime fecha = LocalDateTime.now();
//                Pedido pedido = new Pedido(cliente, articulo, cantidad);
//
//                pedido.setTotal(total);
//
//                // Añadir el pedido a la lista de pedidos
//                datos.getListaPedidos().addElemento(pedido);
//
//                System.out.println("Pedido añadido correctamente.");
//                System.out.print(pedido.toString());
//                System.out.println("Envio con descuento: " + precioConDescuento + "€\n");
//
//            }
//        } catch (Exception e) {
//            System.out.println("Se ha producido un error: " + e.getMessage());
//        }
//    }
//
//    public void eliminarPedido1 (int numPedido){
//        boolean encontrado = false;
//        Pedido pedidoEncontrado = null;
//
//        //Mostrar pedidos disponibles
//
//        for (Pedido pedido : datos.getListaPedidos().getLista()) {
//            // Comprobar si el num del pedido actual es igual a un num de pedido de la lista
//            if (pedido.getNumPedido() == numPedido) {
//                encontrado = true;
//                pedidoEncontrado = pedido;
//            }
//        }
//
//        if(encontrado){
//            for (Articulo articulo : datos.getListaArticulos().getLista()) {
//                //Busqueda de tiempo de preparacion del articulo
//                if (articulo.getCodigo().equals(pedidoEncontrado.getArticulo().getCodigo())) {
//                    //Comprueba si envio no ha sido enviado*/
//                    if(!pedidoEncontrado.pedidoEnviado()){
//                        //Confirmacion de borrado
//                            datos.getListaPedidos().getLista().remove(pedidoEncontrado);
//                            System.out.println("El pedido" + numPedido + " ha sido eliminado con exito.");
//
//                    } else {
//                        //No borra por que esta enviado y setea isEnviado del pedido a true
//                        System.out.println("El pedido ya esta enviado y por tanto no se puede eliminar.");
//                        pedidoEncontrado.setEnviado(true);
//                    }
//                }
//            }
//        }
//        //Pedido no existe asi que sale de la opcion de eliminar
//        else {
//            System.out.println("El número de pedido no corresponde a ningún pedido existente.");
//        }
//    }
//
//    public void filtrarPedidosPendientesPorNombreCliente1(String email) {
//        boolean encontrado = false;
//        Cliente clienteEncontrado = null;
//        if(datos.getListaPedidos().getLista().size()>0) {
//            for (Cliente cliente : datos.getListaClientes().getLista()) {
//                if (cliente.getEmail().equals(email)) {
//                    encontrado = true;
//                    clienteEncontrado = cliente;
//                }
//            }
//            if (encontrado) {
//                for (Pedido pedido : datos.getListaPedidos().getLista()) {
//                    //Busqueda de tiempo de preparacion del articulo
//                    if (pedido.getCliente().equals(clienteEncontrado)) {
//                            if (!pedido.pedidoEnviado()) {
//                                pedido.setEnviado(false);
//                                System.out.println(pedido.toString());
//                            }
//                    }
//                }
//            } else {
//                System.out.println("El cliente no fue encontrado.");
//            }
//        } else {
//            System.out.println("No hay pedidos para mostrar.");
//        }
//    }
//
//    public void filtrarPedidosPendientes1(){
//        if(datos.getListaPedidos().getLista().size()>0){
//            for (Pedido pedido : datos.getListaPedidos().getLista()) {
//                //Busqueda de tiempo de preparacion del articulo
//
//                    if (!pedido.pedidoEnviado()){
//                        pedido.setEnviado(false);
//                        System.out.println(pedido.toString());
//                    }
//
//            }
//        } else {
//            System.out.println("No hay pedidos para mostrar.");
//        }
//
//    }
//
//    public void filtrarPedidosEnviadosPorNombreCliente1(String email) {
//        boolean encontrado = false;
//        Cliente clienteEncontrado = null;
//        if(datos.getListaPedidos().getLista().size()>0) {
//            for (Cliente cliente : datos.getListaClientes().getLista()) {
//                if (cliente.getEmail().equals(email)) {
//                    encontrado = true;
//                    clienteEncontrado = cliente;
//                }
//            }
//            if (encontrado) {
//                for (Pedido pedido : datos.getListaPedidos().getLista()) {
//                    //Busqueda de tiempo de preparacion del articulo
//                    if (pedido.getCliente().equals(clienteEncontrado)) {
//                            if (pedido.pedidoEnviado()) {
//                                pedido.setEnviado(true);
//                                System.out.println(pedido.toString());
//                            }
//                    }
//                }
//            } else {
//                System.out.println("El cliente no fue encontrado.");
//            }
//        } else {
//            System.out.println("No hay pedidos para mostrar.");
//        }
//    }
//
//    public void filtrarPedidosEnviados1 (){
//        if(datos.getListaPedidos().getLista().size()>0){
//            for (Pedido pedido : datos.getListaPedidos().getLista()) {
//                //Busqueda de tiempo de preparacion del articulo
//                    if (pedido.pedidoEnviado()){
//                        pedido.setEnviado(true);
//                        System.out.println(pedido.toString());
//                    }
//            }
//        } else {
//            System.out.println("No hay pedidos para mostrar.");
//        }
//
//    }

    /**
     * metodo creación articulos recibe parametros de vista y ejecuta metodos de factoryDao
     * @param codigo
     * @param descripcion
     * @param precioVenta
     * @param gastosEnvio
     * @param tiempoPreparacion
     * @throws IOException
     */
    public void crearArticulo(String codigo, String descripcion, float precioVenta, float gastosEnvio, long tiempoPreparacion) throws IOException {///metodo creado posteriormente para añadir a la BBDD
            con = new Conexion();
        try {
            Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
            ArticuloDao dao = new factoryArticuloDAO(con.getConnection());
            dao.insertar(articulo);
            System.out.println("Articulo creado:\n" + articulo);
            con.desconectarBD();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear el artículo: " + e.getMessage());
        }
    }

    /**
     * metodo listar todos los articulos, llama al metodo obtener todos de factoryArticuloDao
     * @throws IOException
     */
    public void listarArticulos() throws IOException {
        con = new Conexion();
        try{
            ArticuloDao dao = new factoryArticuloDAO(con.getConnection());
            List<Articulo> articulos = dao.obtenerTodos();
            for (Articulo a: articulos){
                System.out.println(a.toString());
            }
            con.desconectarBD();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * metodo creacion clientes, recibe parametros de la vista e interactua con factoryClienteEstandaraDao y factoryClientePremiumDao
     * @param nombre
     * @param domicilio
     * @param nif
     * @param email
     * @param opcion
     * @throws IOException
     */
    public void crearCliente(String nombre, String domicilio, String nif, String email, int opcion) throws IOException {
        con = new Conexion();
        try {
            if (opcion == 1) {
                ClienteNormal clienteNormal = new ClienteNormal(nombre, domicilio, nif, email);
                ClienteNormalDao dao = new factoryClienteEstandarDAO(con.getConnection());
                dao.insertar(clienteNormal);
                System.out.println("Cliente Estandar añadido.\n" + clienteNormal);
                con.desconectarBD();
            }
            if (opcion == 2 ) {
                ClientePremium clientePremium = new ClientePremium(nombre, domicilio, nif, email);
                ClientePremiumDao dao = new factoryClientePremiumDAO(con.getConnection());
                dao.insertar(clientePremium);
                System.out.println("Cliente premiun añadido.\n" + clientePremium);
                con.desconectarBD();
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear el cliente: " + e.getMessage());
            con.desconectarBD();
        }
    }

    /**
     * metodo listar todos los clientes Estandard de factoryClienteEstandarDAO
     * @throws IOException
     */
    public void listarClientesNormal() throws IOException {
        con = new Conexion();
        try{
            ClienteNormalDao dao = new factoryClienteEstandarDAO(con.getConnection());
            List<ClienteNormal> clienteNormals = dao.obtenerTodos();
            for (ClienteNormal a: clienteNormals){
                System.out.println(a.toString());
            }
            con.desconectarBD();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * metodo listar todos los clientes Premium de factoryClientePremiumDAO
     * @throws IOException
     */
    public void listarClientesPremium() throws IOException {
        con = new Conexion();
        try{
            ClientePremiumDao dao = new factoryClientePremiumDAO(con.getConnection());
            List<ClientePremium> clientePremiums = dao.obtenerTodos();
            for (ClientePremium a: clientePremiums){
                System.out.println(a.toString());
            }
            con.desconectarBD();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * Une las dos listas de clientes Estandard y Premium
     * @throws IOException
     */
    public void listarTodosClientes() throws IOException {
        con = new Conexion();
        try{

            ClienteNormalDao dao1 = new factoryClienteEstandarDAO(con.getConnection());
            List<ClienteNormal> clienteNormals = dao1.obtenerTodos();
            for (ClienteNormal a: clienteNormals){
                System.out.println(a.toString());
            }
            ClientePremiumDao dao = new factoryClientePremiumDAO(con.getConnection());
            List<ClientePremium> clientePremiums = dao.obtenerTodos();
            for (ClientePremium a: clientePremiums){
                System.out.println(a.toString());
            }
            con.desconectarBD();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }


    /**
     * Metodo para listar todos los pedidos pendientes, interactua con el metodo obtenerTodosPendientes de factoryPedido
     * @throws IOException
     */
    public void filtrarPedidosPendientes() throws IOException {
        con = new Conexion();
        try{
            factoryPedido dao = new factoryPedido(con.getConnection());
            dao.obtenerTodosPendienes();
            con.desconectarBD();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }


    /**
     * Creacion del pedido, recibe los parametros de la vista, email, crea el pedido comprobando si existe el cliente
     * e interactua con factoryPedido para generar el pedido
     * @param email
     * @throws IOException
     * @throws DAOException
     */
    public void crearPedido(String email) throws IOException, DAOException {
        con = new Conexion();
        ClientePremiumDao clientePremiumDao = new factoryClientePremiumDAO(con.getConnection());
        ClienteNormalDao clienteNormalDao = new factoryClienteEstandarDAO(con.getConnection());
        ArticuloDao articuloDao = new factoryArticuloDAO(con.getConnection());

        try {
            try{
                if (clienteNormalDao.existe(clienteNormalDao.obtener(email)) || clientePremiumDao.existe(clientePremiumDao.obtener(email)) == true){
                    System.out.println("Cliente existe");
                    System.out.println("Introduzca el código del producto");
                    String codigo = s.nextLine();
                    try{
                        if (articuloDao.obtener(codigo)!= null) {
                            System.out.println("Introduzca la cantidad: ");
                            int cantidad = s.nextInt();
                            s.nextLine();
                            Pedido pedido = new Pedido(clienteNormalDao.obtener(email), articuloDao.obtener(codigo), cantidad);
                            factoryPedido dao = new factoryPedido(con.getConnection());
                            System.out.println("Pedido añadido!!");
                            dao.insertar(pedido);
                            con.desconectarBD();
                        }
                    }catch (Exception ex){
                        System.out.println("Error en el articulo "+ ex);
                    }
                }
            }catch (Exception ex){
                con.desconectarBD();
                System.out.println("Cliente no existe");
                System.out.println("Error en el registro: " + ex.getMessage());
                System.out.println("El cliente con correo electrónico " + email + " no existe.");
                System.out.println("Creando nuevo cliente...");
                GestionClientes gestionClientes = new GestionClientes();
                gestionClientes.datosCliente(this);
            }
        } catch (Exception e) {
            System.out.println("Error en el registro: " + e.getMessage());
        }

    }

    /**
     * metodo filtrar los pedidos pendientes por cliente, recibe el email y muestra sus pedidos pendientes.
     * @param email
     * @throws IOException
     */
    public void filtrarPedidosPendientesPorNombreCliente(String email) throws IOException {
        con = new Conexion();
        try{
            factoryPedido dao = new factoryPedido(con.getConnection());
            dao.obtenerPendientesCliente(email);
            con.desconectarBD();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * metodo eliminación pedido, para ello le indicamos el número de pedido
     * @param numPedido
     * @throws IOException
     */
    public void eliminarPedido (int numPedido) throws IOException {
        con = new Conexion();
        try{
            factoryPedido dao = new factoryPedido(con.getConnection());
            dao.eliminar(numPedido);
            System.out.println("Pedido eliminado correctamente!!");
            System.out.println("Pedidos pendientes ahora: ");
            dao.obtenerTodosPendienes();
            con.desconectarBD();
        }catch(Exception ex){
            System.out.println("No se ha podido eliminar el pedido, está ya enviado ");
        }
    }

    /**
     *metodo para mostrar los pedidos enviados.
     * @throws IOException
     */
    public void filtrarPedidosEnviados () throws IOException {
        con = new Conexion();
        try{
            factoryPedido dao = new factoryPedido(con.getConnection());
            dao.obtenerTodosEnviados();
            con.desconectarBD();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * metodo para filtrar los pedidos por el mail del cliente que le enviamos desde la vista
     * @param email
     * @throws IOException
     */
    public void filtrarPedidosEnviadosPorNombreCliente(String email) throws IOException {
        con = new Conexion();
        ClientePremiumDao clientePremiumDao = new factoryClientePremiumDAO(con.getConnection());
        ClienteNormalDao clienteNormalDao = new factoryClienteEstandarDAO(con.getConnection());

        try{
            if (clienteNormalDao.existe(clienteNormalDao.obtener(email)) || clientePremiumDao.existe(clientePremiumDao.obtener(email)) == true){
                System.out.println("Existe el cliente");
            }
            factoryPedido dao = new factoryPedido(con.getConnection());
            dao.obtenerEnviadosCliente(email);
            con.desconectarBD();
        }catch(Exception ex){
            System.out.println("Error en la conexion "+ex);
        }
    }
}
