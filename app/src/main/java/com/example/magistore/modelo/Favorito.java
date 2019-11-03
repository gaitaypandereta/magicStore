package com.example.magistore.modelo;

import java.util.Date;

public class Favorito {
    private Date fecha;
    private String codigo_general_producto;

    public Favorito(Date fecha, String codigo_producto) {
        this.fecha = fecha;
        this.codigo_general_producto = codigo_producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodigo_general_producto() {
        return codigo_general_producto;
    }

    public void setCodigo_producto(String codigo_general_producto) {
        this.codigo_general_producto = codigo_general_producto;
    }
}


