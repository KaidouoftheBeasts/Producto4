package beepod.vista;

import beepod.controlador.Controlador;

import java.util.Scanner;

/**
 * Clase para la gestión de los pedidos
 */
public class GestionPedidos {

    Scanner teclado = new Scanner(System.in);
   public GestionPedidos(){

    }

    public void inicio(Controlador control){
        boolean salir = false;
        char opcio;


        do{
            System.out.println("1. Añadir Pedido");
            System.out.println("2. Eliminar Pedido");
            System.out.println("3. Mostrar Pedidos pendientes");
            System.out.println("4. Mostrar Pedidos enviados");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio){
                case '1':
                    datosPedido(control);
                    break;
                case '2':
                    break;
                case '3':
                    break;
                case '4':
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
        String resp;
        System.out.println("Elige la opcion (1,2,3,4 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

    public void datosPedido(Controlador control) {
        // Pedir el correo electrónico del cliente
        System.out.println("Introduce el correo electrónico del cliente: ");
        String email = teclado.nextLine();
        control.crearPedido(email);
    }
}
