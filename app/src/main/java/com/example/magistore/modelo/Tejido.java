package com.example.magistore.modelo;

public class Tejido {
    private String id;
    private String nombre;
    private String descripcion;
    private String megusta;
    private String url_img;


    public Tejido(String nombre, String descripcion, String megusta, String url_img) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.megusta = megusta;
        this.url_img = url_img;
    }

    public Tejido(String id, String nombre, String descripcion, String megusta, String url_img) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.megusta = megusta;
        this.url_img = url_img;
    }

    public Tejido() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMegusta() {
        return megusta;
    }

    public void setMegusta(String megusta) {
        this.megusta = megusta;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
}
