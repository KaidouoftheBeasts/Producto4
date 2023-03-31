package beepod.modelo;

public class ClienteNormal extends Cliente{
    /*Constructores*/
    public ClienteNormal() {
    }

    public ClienteNormal(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    @Override
    public String tipoCliente() {
        return "Estandar";
    }

    @Override
    public float calcAnual() {
        return 0.0f;
    }

    @Override
    public float descuentoEnv() {
        return 0.0f;
    }
/*
* toString
* */
    @Override
    public String toString() {
        return super.toString();
    }
}
