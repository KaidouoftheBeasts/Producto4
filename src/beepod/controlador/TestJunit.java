package beepod.controlador;
import beepod.modelo.*;
import junit.framework.TestCase;
import org.junit.Test;

public class TestJunit extends TestCase {

    public void TestJunit(){
    }

    @Test
    public void testArticuloGetGastosEnvio(){
        Articulo articulo = new Articulo("1", "Prueba Test", 20, 10, 10);
        float resultadoEsperado = 10;
        float resultado = articulo.getGastosEnvio();
        assertTrue("Resultado no esperado ",resultadoEsperado==resultado);
        System.out.println("La función funciona correctamente");

    }
    @Test
    public void testTotalPedido(){

        Articulo articulo = new Articulo("1","Es Una prueba",20,10,10 );
        ClientePremium clientePremium = new ClientePremium("Jose","prueba","1234","email");
        Pedido pedido = new Pedido();
        float resultadoEsperado = 208;
        float resultado = pedido.totalPedido(10,articulo,clientePremium);
        assertTrue("El pedido no lo calcula correctamente ", resultadoEsperado==resultado);
        System.out.println("El pedido está correcto");
    }
}



