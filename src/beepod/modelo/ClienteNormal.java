package beepod.modelo;

public class ClienteNormal extends Cliente{
    /*Constructores*/
    public ClienteNormal() {
    }

    public ClienteNormal(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }
/*
* toString
* */
    @Override
    public String toString() {
        return super.toString();
    }
}
