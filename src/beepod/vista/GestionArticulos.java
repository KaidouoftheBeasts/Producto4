package beepod.vista;

import beepod.controlador.Controlador;

import java.util.Scanner;

/**
 * Clase para la gestión de los Articulos
 */
public class GestionArticulos {
    Controlador control = new Controlador();
    Scanner teclado = new Scanner(System.in);
   public GestionArticulos(){

    }

    public void inicio(){
        boolean salir = false;
        char opcio;



        do{
            System.out.println("1. Añadir Articulos");
            System.out.println("2. Mostrar Articutlos");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio){
                case '1':
                    control.crearArticulo();
                    break;
                case '2':
                    System.out.println("-----------------------------------------Lista de los articulos---------------------------------------\n");
                    control.listarArticulos();
                    System.out.println("-------------------------------------------------------------------------------------------------------\n");
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
        System.out.println("Elige la opcion (1,2 o 0)");
        resp = teclado.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

}
