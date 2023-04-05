package beepod.controlador;
import beepod.modelo.Pedido;
import junit.framework.TestCase;
import org.junit.Test;

public class TestJunit extends TestCase {
    private Pedido pedido;


    public void escenario (){
        pedido = new Pedido();
    }
    @Test
    public void testTotalEnvio(){
        escenario();
        int cantidad = 20;


    }

}
