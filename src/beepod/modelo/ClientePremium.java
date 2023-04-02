package beepod.modelo;

public class ClientePremium extends Cliente {
    //Atributos exclusivos
    private float cuota;
    private float descuento;

    public ClientePremium(){

    }
    public ClientePremium(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
        cuota = 30;
        descuento = (float) 0.2;
    }
/*
* Setters y getters
* */
    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    @Override
    public String tipoCliente() {
        return "Premium";
    }

    @Override
    public float calcAnual() {
        return 30.0f;
    }

    @Override
    public float descuentoEnv() {
        return 0.2f;
    }
/*
toString
 */
    @Override
    public String toString() {
        return  "Cliente Premium: "+super.toString()+" cuota: "+cuota+" €uros" +" descuento "+descuento+ " %";
    }
}
