package com.example.magistore.modelo;

import java.io.Serializable;

public class Compra implements Serializable {
    private String user;
    private String foto;
    private String titulo ;
    private int megusta;


    public Compra(String titulo, String url_img, String user, int megusta) {
        if(titulo.trim() == ""){
            titulo="No se introducido dato";
        }

        this.user = user;
        this.foto = url_img;
        this.titulo = titulo;
        this.megusta = megusta;

    }

    public Compra(String user, String url_img, String titulo) {
        this.user=user;
        this.foto = url_img;
        this.titulo = titulo;

    }



    public Compra() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getMegusta() {
        return megusta;
    }

    public void setMegusta(int megusta) {
        this.megusta = megusta;
    }


}
