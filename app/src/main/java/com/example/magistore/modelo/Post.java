package com.example.magistore.modelo;

import java.io.Serializable;

public class Post implements Serializable {
    private String id;
    private String user;
    private String url_img;
    private String descripcion;
    private int megusta;


    public Post(String id, String user, String descripcion, String url_img, int megusta) {
        this.id=id;
        this.user = user;
        this.url_img = url_img;
        this.descripcion = descripcion;
        this.megusta = megusta;

    }

    public Post(String id, String user, String url_img, String titulo) {
        this.id=id;
        this.user=user;
        this.url_img = url_img;
        this.descripcion = titulo;

    }



    public Post() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getDecripcion() {
        return descripcion;
    }

    public void setDecripcion(String decripcion) {
        this.descripcion = decripcion;
    }

    public int getMegusta() {
        return megusta;
    }

    public void setMegusta(int megusta) {
        this.megusta = megusta;
    }


}
