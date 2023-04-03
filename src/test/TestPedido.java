
package test;

import beepod.modelo.Articulo;
import beepod.modelo.ClientePremium;
import beepod.modelo.Pedido;
import junit.framework.TestCase;
import org.junit.Test;

public class TestPedido extends TestCase {
   public Pedido pedido;
   public Articulo articulo;
   public ClientePremium clientePremium;


    public void inicializar(){
    pedido = new Pedido();
    articulo = new Articulo("1","Es Una prueba",20,10,10 );
    clientePremium = new ClientePremium("Jose","prueba","1234","email");
    }
    @Test
    public void testPedido(){
        inicializar();
        float resultadoEsperado = 208;
        float resultado = pedido.totalPedido(10,articulo,clientePremium);
        assertEquals(resultadoEsperado, resultado);
    }
}



