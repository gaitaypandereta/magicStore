package com.example.magistore.modelo;

import java.util.Date;

public class Compra {
    private String url_img, titulo, megusta, comenta;


    public Compra(String url_img, String titulo, String megusta, String comenta) {
        this.url_img = url_img;
        this.titulo = titulo;
        this.megusta = megusta;
        this.comenta = comenta;
    }

    public Compra() {
    }



    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMegusta() {
        return megusta;
    }

    public void setMegusta(String megusta) {
        this.megusta = megusta;
    }

    public String getComenta() {
        return comenta;
    }

    public void setComenta(String comenta) {
        this.comenta = comenta;
    }
}
