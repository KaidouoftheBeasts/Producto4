package beepod.vista;

import beepod.controlador.Controlador;

import java.util.Scanner;

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
        String resp;
        System.out.println("Elige la opcion (1,2,3,4 o 0");
        resp = teclado.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

}
