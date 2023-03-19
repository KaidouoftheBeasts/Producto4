package beepod.vista;

import beepod.controlador.Controlador;

import java.util.Scanner;

public class GestionOs {
    private Controlador controlador;
    Scanner teclado = new Scanner(System.in);
   public GestionOs(){
        controlador = new Controlador();
    }

    public void inicio(){
        boolean salir = false;
        char opcio;



        do{
            System.out.println("1. Gestión Articulos");
            System.out.println("2. Gestión Clientes");
            System.out.println("3. Gestión Pedidos");
            System.out.println("0. Salir22");
            opcio = pedirOpcion();
            switch (opcio){
                case '1':
                    break;
                case '2':
                    break;
                case '3':
                    break;
                case '0':
                    salir = true;

            }

        }while (!salir);
    }

    private char pedirOpcion() {
        String resp;
        System.out.println("Elige la opcion (1,2,3 o 0");
        resp = teclado.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

}
