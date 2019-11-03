package com.example.magistore.modelo;

import java.util.Date;

public class Compra {
private Date fecha_compra;
private Articulo articulo_compra;
private boolean estado;
private Date fecha_envio;

    public Compra(Date fecha_compra, Articulo articulo_compra, boolean estado, Date fecha_envio) {
        this.fecha_compra = fecha_compra;
        this.articulo_compra = articulo_compra;
        this.estado = estado;
        this.fecha_envio = fecha_envio;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Articulo getArticulo_compra() {
        return articulo_compra;
    }

    public void setArticulo_compra(Articulo articulo_compra) {
        this.articulo_compra = articulo_compra;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(Date fecha_envio) {
        this.fecha_envio = fecha_envio;
    }
}
