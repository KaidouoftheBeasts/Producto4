package beepod.vista;

import beepod.controlador.Controlador;

import java.util.Scanner;

/**
 * Clase para la gestión de los clientes
 */
public class GestionClientes {
    private Controlador control = new Controlador();

    Scanner teclado = new Scanner(System.in);
   public GestionClientes(){

    }

    public void inicio(){
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
                    control.crearClientes();
                    break;
                case '2':
                    System.out.println("----------------------------------------Lista de todos los Clientes------------------------------------\n");
                    control.listarTodosClientes();
                    System.out.println("-------------------------------------------------------------------------------------------------------\n");
                    break;
                case '3':
                    System.out.println("------------------------------------------Lista los clientes Standar------------------------------------\n");
                    control.listarClientesNormal();
                    System.out.println("--------------------------------------------------------------------------------------------------------\n");
                    break;
                case '4':
                    System.out.println("-------------------------------------------Lista los clientes Premium-----------------------------------\n");
                    control.listarClientesPremium();
                    System.out.println("---------------------------------------------------------------------------------------------------------\n");
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
        System.out.println("Elige la opcion (1,2,3,4 o 0");
        resp = teclado.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

}
