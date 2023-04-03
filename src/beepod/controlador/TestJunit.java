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
        assertEquals(resultadoEsperado,resultado);
        fail("El Test es ok!!");
    }
    //String codigo, String descripcion, float precioVenta, float gastosEnvio, long tiempoPreparacion
    @Test
    public void testTotalPedido(){

        Articulo articulo = new Articulo("1","Es Una prueba",20,10,10 );
        ClientePremium clientePremium = new ClientePremium("Jose","prueba","1234","email");
        Pedido pedido = new Pedido();
        float resultadoEsperado = 208;
        float resultado = pedido.totalPedido(10,articulo,clientePremium);
        assertEquals(resultadoEsperado,resultado);
        fail("El Test del pedido es ok!!");
    }
}
