package com.example.magistore.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("user")
    private String user;

    @Expose
    @SerializedName("url_img")
    private String url_img;

    @Expose
    @SerializedName("descripcion")
    private String descripcion;

    @Expose
    @SerializedName("megusta")
    private String megusta;


    public Post(String id, String user, String descripcion, String url_img, String megusta) {
        this.id=id;
        this.user = user;
        this.url_img = url_img;
        this.descripcion = descripcion;
        this.megusta = megusta;

    }

    public Post(String user, String url_img, String titulo,  String megusta) {
        this.user=user;
        this.url_img = url_img;
        this.descripcion = titulo;
        this.megusta=megusta;

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

    public String getMegusta() {
        return megusta;
    }

    public void setMegusta(String megusta) {
        this.megusta = megusta;
    }


}
