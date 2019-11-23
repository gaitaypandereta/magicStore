package com.example.magistore.modelo;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private String facebook;
    private String twitter;
    private String instagram;
    private String direccion;
    private String edad;
    private String sexo;
    private String pass;
    private int  saldo=0;
    private List<Compra> listCompras;
    private List<Favorito> listFavoritos;

    public Usuario(String id, String facebook, String twitter, String instagram, String direccion) {
        this.id = id;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.direccion = direccion;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String email, String telefono, String direccion, String edad, String sexo, String facebook, String twitter, String instagram, String pass, int saldo) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edad = edad;
        this.sexo = sexo;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.pass = pass;
        this.saldo = saldo;
    }

    public Usuario(String nombre, String email, String telefono, String facebook, String twitter, String instagram, String direccion, String edad, String sexo, String pass) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.direccion = direccion;
        this.edad = edad;
        this.sexo = sexo;
        this.pass = pass;

    }

    public Usuario(String id, String nombre, String email, String telefono, String facebook, String twitter, String instagram, String direccion, String edad, String sexo, String pass, int saldo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.direccion = direccion;
        this.edad = edad;
        this.sexo = sexo;
        this.pass = pass;
        this.saldo = saldo;
    }

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Usuario(String id, String nombre, String email, String telefono, String facebook, String twitter, String instagram, String direccion, String edad, String sexo, String pass, int saldo, List<Compra> listCompras, List<Favorito> listFavoritos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.direccion = direccion;
        this.edad = edad;
        this.sexo = sexo;
        this.pass = pass;
        this.saldo = saldo;
        this.listCompras = listCompras;
        this.listFavoritos = listFavoritos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario(String telefono, String facebok, String twiter, String instagra, String direccion, String edad, String sex) {
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public List<Compra> getListCompras() {
        return listCompras;
    }

    public void setListCompras(List<Compra> listCompras) {
        this.listCompras = listCompras;
    }

    public List<Favorito> getListFavoritos() {
        return listFavoritos;
    }

    public void setListFavoritos(List<Favorito> listFavoritos) {
        this.listFavoritos = listFavoritos;
    }
}
