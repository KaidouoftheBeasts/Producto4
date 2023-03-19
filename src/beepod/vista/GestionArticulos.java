package beepod.vista;

import beepod.controlador.Controlador;

import java.util.Scanner;

public class GestionArticulos {

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
                    break;
                case '2':
                    break;
                case '0':
                    salir = true;

            }

        }while (!salir);
    }

    private char pedirOpcion() {
        String resp;
        System.out.println("Elige la opcion (1,2 o 0");
        resp = teclado.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

}
