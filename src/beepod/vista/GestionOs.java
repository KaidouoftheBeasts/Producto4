package beepod.vista;

import beepod.controlador.Controlador;
import java.util.Scanner;

/**
 * Vista del menú principal
 */
public class GestionOs {
    private Controlador control = new Controlador();
    Scanner teclado = new Scanner(System.in);


    public void inicio(){
        boolean salir = false;
        char opcio;



        do{
            System.out.println("1. Gestión Articulos");
            System.out.println("2. Gestión Clientes");
            System.out.println("3. Gestión Pedidos");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio){
                case '1':
                    GestionArticulos gestionArticulos = new GestionArticulos();
                    gestionArticulos.inicio(control);
                    break;
                case '2':
                    GestionClientes gestionClientes = new GestionClientes();
                    gestionClientes.inicio(control);
                    break;
                case '3':
                    GestionPedidos gestionPedidos = new GestionPedidos();
                    //gestionPedidos.inicio(control);
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
        System.out.println("Elige la opcion (1,2,3 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

}
