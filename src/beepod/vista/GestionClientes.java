package beepod.vista;

import java.util.Scanner;

public class GestionClientes {

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
                    break;
                case '2':
                    break;
                case '3':
                    break;
                case '4':
                    break;
                case '0':
                    salir = true;

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
