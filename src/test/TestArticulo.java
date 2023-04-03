
package test;
import beepod.modelo.*;
import junit.framework.TestCase;
import org.junit.Test;
public class TestArticulo extends TestCase {
   public Articulo articulo;


    public void inicializar(){
       articulo= new Articulo("5", "hola",5,12,2);
    }
    @Test
    public void testSetArticulo(){
        inicializar();
        articulo.setCodigo("5");
        assertEquals("5",articulo.getCodigo());

    }
}



