package com.example.magistore.modelo;

import java.util.List;

public class Articulo {
private String nombre_articulo;
private String codigo_general_articulo;
private String codigo_articulo;
private String talla;
private String color;
private String tipo;
private List<Usuario> listaUsuarios_favorito;
private Boolean estado;


    public Articulo() {
    }

    public Articulo(String nombre_articulo, String codigo_general_articulo, String codigo_articulo, String talla, String color, String tipo, List<Usuario> listaUsuarios_favorito, Boolean estado) {
        this.nombre_articulo = nombre_articulo;
        this.codigo_general_articulo = codigo_general_articulo;
        this.codigo_articulo = codigo_articulo;
        this.talla = talla;
        this.color = color;
        this.tipo = tipo;
        this.listaUsuarios_favorito = listaUsuarios_favorito;
        this.estado = estado;
    }

    public Articulo(String nombre_articulo, String codigo_general_articulo, String codigo_articulo, String tipo, List<Usuario> listaUsuarios_favorito, Boolean estado) {
        this.nombre_articulo = nombre_articulo;
        this.codigo_general_articulo = codigo_general_articulo;
        this.codigo_articulo = codigo_articulo;
        this.tipo = tipo;
        this.listaUsuarios_favorito = listaUsuarios_favorito;
        this.estado = estado;
    }

    public String getNombre_articulo() {
        return nombre_articulo;
    }

    public void setNombre_articulo(String nombre_articulo) {
        this.nombre_articulo = nombre_articulo;
    }

    public String getCodigo_general_articulo() {
        return codigo_general_articulo;
    }

    public void setCodigo_general_articulo(String codigo_general_articulo) {
        this.codigo_general_articulo = codigo_general_articulo;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public void setCodigo_articulo(String codigo_articulo) {
        this.codigo_articulo = codigo_articulo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Usuario> getListaUsuarios_favorito() {
        return listaUsuarios_favorito;
    }

    public void setListaUsuarios_favorito(List<Usuario> listaUsuarios_favorito) {
        this.listaUsuarios_favorito = listaUsuarios_favorito;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
