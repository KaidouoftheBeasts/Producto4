package beepod.modelo;

import java.util.Date;

public class Pedido {
    //Atributos
    private int numPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private Date fecha;
    private boolean enviado;
/*Constructores
* */

    public Pedido() {
    }

    public Pedido(int numPedido, Cliente cliente, Articulo articulo, int cantidad, Date fecha, boolean enviado) {
        this.numPedido = numPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.enviado = enviado;
    }
/*
* Getters y Setters
* */
    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
/*
* toString*/
    @Override
    public String toString() {
        return "Pedido{" +
                "numPedido=" + numPedido +
                ", cliente=" + cliente +
                ", articulo=" + articulo +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", enviado=" + enviado +
                '}';
    }
}
