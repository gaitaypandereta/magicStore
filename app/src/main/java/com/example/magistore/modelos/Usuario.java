package com.example.magistore.modelos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("telefono ")
    @Expose
    private String telefono;

    @SerializedName("direccion")
    @Expose
    private String direccion;

    @SerializedName("edad_compra")
    @Expose
    private String edadCompra;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("facebook")
    @Expose
    private String facebook;

    @SerializedName("instagram")
    @Expose
    private String instagram;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("sexo_compra")
    @Expose
    private String sexoCompra;

    @SerializedName("comenta_admin")
    @Expose
    private String comentaAdmin;

    @SerializedName("twitter")
    @Expose
    private String twitter;
/*
    public Usuario(String id, String telefono, String direccion, String edadCompra, String email, String facebook, String instagram, String nombre, String sexoCompra, String comentaAdmin,  String twitter) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edadCompra = edadCompra;
        this.email = email;
        this.facebook = facebook;
        this.instagram = instagram;
        this.nombre = nombre;
        this.sexoCompra = sexoCompra;
        this.comentaAdmin=comentaAdmin;
        this.twitter = twitter;
    }

   */


    public Usuario(String id, String telefono, String direccion, String edadCompra, String email, String facebook, String instagram, String nombre, String sexoCompra, String comentaAdmin, String twitter) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edadCompra = edadCompra;
        this.email = email;
        this.facebook = facebook;
        this.instagram = instagram;
        this.nombre = nombre;
        this.sexoCompra = sexoCompra;
        this.comentaAdmin=comentaAdmin;
        this.twitter = twitter;
    }

    public Usuario(String telefono, String direccion, String edad, String email, String faceboo, String instagra, String nombre, String sexo, String estado, String twitte) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edadCompra = edad;
        this.email = email;
        this.facebook = faceboo;
        this.instagram = instagra;
        this.nombre = nombre;
        this.sexoCompra = sexo;
        this.twitter = twitte;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEdadcompra() {
        return edadCompra;
    }

    public void setEdadcompra(String edadcompra) {
        this.edadCompra = edadcompra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexocompra() {
        return sexoCompra;
    }

    public void setSexocompra(String sexoCompra) {
        this.sexoCompra = sexoCompra;
    }

    public String getComentaAdmin() {
        return comentaAdmin;
    }

    public void setComentaAdmin(String comentaAdmin) {
        this.comentaAdmin = comentaAdmin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}