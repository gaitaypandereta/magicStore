package com.example.magistore.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuki {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("usuario_cukis")
    @Expose
    private String usuarioCukis;

    @SerializedName("aportaciones")
    @Expose
    private Integer aportaciones;

    @SerializedName("acumuladas")
    @Expose
    private Integer acumuladas;

    @SerializedName("facebook")
    @Expose
    private Integer facebook;

    @SerializedName("instagram")
    @Expose
    private Integer instagram;

    @SerializedName("twitter")
    @Expose
    private Integer twitter;


    @SerializedName("campahna")
    @Expose
    private String campahna;

    public Cuki(String id, Integer facebook, Integer instagram, Integer twitter) {
        this.id = id;
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioCukis() {
        return usuarioCukis;
    }

    public void setUsuarioCukis(String usuarioCukis) {
        this.usuarioCukis = usuarioCukis;
    }

    public Integer getAportaciones() {
        return aportaciones;
    }

    public void setAportaciones(Integer aportaciones) {
        this.aportaciones = aportaciones;
    }

    public Integer getFacebook() {
        return facebook;
    }

    public void setFacebook(Integer facebook) {
        this.facebook = facebook;
    }

    public Integer getInstagram() {
        return instagram;
    }

    public void setInstagram(Integer instagram) {
        this.instagram = instagram;
    }

    public Integer getTwitter() {
        return twitter;
    }

    public void setTwitter(Integer twitter) {
        this.twitter = twitter;
    }

    public String getCampahna() {
        return campahna;
    }

    public void setCampahna(String campahna) {
        this.campahna = campahna;
    }

    public Integer getAcumuladas() {
        return acumuladas;
    }

    public void setAcumuladas(Integer acumuladas) {
        this.acumuladas = acumuladas;
    }
}
