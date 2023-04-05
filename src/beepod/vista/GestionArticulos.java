package beepod.vista;

import beepod.controlador.Controlador;

import java.util.Scanner;

/**
 * Clase para la gestión de los Articulos
 */
public class GestionArticulos {
    Scanner teclado = new Scanner(System.in);
   public GestionArticulos(){

    }

    public void inicio(Controlador control){
        boolean salir = false;
        char opcio;



        do{
            System.out.println("1. Añadir Articulos");
            System.out.println("2. Mostrar Articutlos");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio){
                case '1':
                    datosArticulo(control);
                    break;
                case '2':
                    control.listarArticulos();
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
        System.out.println("Elige la opcion (1,2 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

    public void datosArticulo(Controlador control) {
        try {
            System.out.println("Introduce el codigo: ");
            String codigo = teclado.nextLine();
            System.out.println("Introduce la descripcion: ");
            String descripcion = teclado.nextLine();
            System.out.println("Introduce el precio de venta: ");
            float precioVenta = teclado.nextFloat();
            System.out.println("Introduce los gastos de envio: ");
            float gastosEnvio = teclado.nextFloat();
            System.out.println("Introduce el tiempo de preparación: ");
            long tiempoPreparacion = teclado.nextLong();
            teclado.nextLine();
            control.crearArticulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
        } catch (Exception e) {
            System.out.println("Se ha producido un error al introducir los datos del artículo: " + e.getMessage());
        }
    }
}
