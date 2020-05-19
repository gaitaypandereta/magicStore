package com.example.magistore.modelos;

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
    @SerializedName("decripcion")
    private String descripcion;

    @Expose
    @SerializedName("megusta")
    private String megusta;

    @Expose
    @SerializedName("cukis")
    private String cukis;

    @Expose
    @SerializedName("comenta_admin")
    private String comenta_admin;

    public Post(String id, String user, String url_img, String descripcion, String megusta, String cukis, String comenta_admin) {
        this.id = id;
        this.user = user;
        this.url_img = url_img;
        this.descripcion = descripcion;
        this.megusta = megusta;
        this.cukis = cukis;
        this.comenta_admin = comenta_admin;
    }

    public Post(String id, String user, String descripcion, String url_img, String megusta) {
        this.id=id;
        this.user = user;
        this.url_img = url_img;
        this.descripcion = descripcion;
        this.megusta = megusta;

    }

    public Post(String user, String url_img, String descripcion,  String megusta) {
        this.user=user;
        this.url_img =url_img;
        this.descripcion = descripcion;
        this.megusta=megusta;

    }

    public Post(String user, String url_img, String descripcion) {
        this.user=user;
        this.url_img =url_img;
        this.descripcion = descripcion;
    }
    public Post(String url_img, String descripcion) {
        this.user=user;
        this.url_img =url_img;
        this.descripcion = descripcion;
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
        this.url_img =url_img;
    }

    public String getDecripcion() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public String getCukis() {
        return cukis;
    }

    public void setCukis(String cukis) {
        this.cukis = cukis;
    }

    public String getComenta_admin() {
        return comenta_admin;
    }

    public void setComenta_admin(String comenta_admin) {
        this.comenta_admin = comenta_admin;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", url_img='" + url_img + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", megusta='" + megusta + '\'' +
                '}';
    }
}
