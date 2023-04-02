package beepod.modelo;

import java.time.LocalDateTime;

public class Pedido {
    //Atributos
    private static int ultimoNumPedido = 0;
    private int numPedido;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fecha;
    private boolean enviado;
/*Constructores
* */

    public Pedido() {
    }

    public Pedido(Cliente cliente, Articulo articulo, int cantidad) {
        this.numPedido = ++ultimoNumPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
        this.enviado = false;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
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
                "ID Pedido: " + numPedido +
                ", Cliente: " + cliente +
                ", Articulo: " + articulo +
                ", Cantidad: " + cantidad +
                ", Fecha: " + fecha +
                ", Enviado: " + enviado +
                '}';
    }
}
