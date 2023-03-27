package beepod.modelo;

public class ClientePremium extends Cliente {
    //Atributos exclusivos
    private float cuota;
    private float descuento;

    public ClientePremium(){

    }
    public ClientePremium(String nombre, String domicilio, String nif, String email, float cuota, float descuento) {
        super(nombre, domicilio, nif, email);
        this.cuota = cuota;
        this.descuento = descuento;
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
/*
toString
 */
    @Override
    public String toString() {
        return  "Cliente Premium: "+super.toString()+" cuota: "+cuota+" €uros" +" descuento "+descuento+ " %";
    }
}
