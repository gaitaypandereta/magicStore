package com.example.magistore.modelo;

import java.io.Serializable;

public class Comentario implements Serializable {
    private String texto;
    private Usuario user_coment;

    public Comentario(String texto, Usuario user_coment) {
        this.texto = texto;
        this.user_coment = user_coment;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getUser_coment() {
        return user_coment;
    }

    public void setUser_coment(Usuario user_coment) {
        this.user_coment = user_coment;
    }
}
