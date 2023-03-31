package beepod.vista;

import beepod.controlador.Controlador;

import java.util.Scanner;

/**
 * Clase para la gestión de los clientes
 */
public class GestionClientes {
    Scanner teclado = new Scanner(System.in);
    public GestionClientes(){ }

    public void inicio(Controlador control){
        boolean salir = false;
        char opcio;


        do{
            System.out.println("1. Añadir Clientes");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Mostrar ClientesStandar");
            System.out.println("4. Mostrar ClientesPremium");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio){
                case '1':
                    datosCliente(control);
                    break;
                case '2':
                    control.listarTodosClientes();
                    break;
                case '3':
                    control.listarClientesNormal();
                    break;
                case '4':
                    control.listarClientesPremium();
                    break;
                case '0':
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta!!");

            }

        }while (!salir);
    }

    private char pedirOpcion() {
        teclado.nextLine();
        String resp;
        System.out.println("Elige la opcion (1,2,3,4 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

    public void datosCliente(Controlador control) {
        System.out.println("Introduce el nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Introduce el domicilio: ");
        String domicilio = teclado.nextLine();
        System.out.println("Introduce el nif: ");
        String nif = teclado.nextLine();
        System.out.println("Introduce el email: ");
        String email = teclado.nextLine();
        System.out.println("Introduce el tipo de cliente: '1' para cliente Normal o '2' para Premium");
        int opcion = teclado.nextInt();
        control.crearCliente(nombre, domicilio, nif, email, opcion);
    }

}
