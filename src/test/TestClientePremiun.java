package test;


import beepod.modelo.ClientePremium;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestClientePremiun {

    ClientePremium clientePremium;

    public void inicializar(){
        clientePremium = new ClientePremium("Jose","prueba","1234","email");
    }
    @Test
    public void testSetArticulo(){
        inicializar();
        //clientePremium.setNombre("Jose");
        assertEquals("Jose",clientePremium.getNombre());
    }


}
