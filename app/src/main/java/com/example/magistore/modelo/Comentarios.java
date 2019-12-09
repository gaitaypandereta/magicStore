package com.example.magistore.modelo;

import java.io.Serializable;
import java.util.List;

public class Comentarios implements Serializable{
    private List<Comentario> comentario;

    public Comentarios(){}

    public Comentarios(List<Comentario> comentario) {
        this.comentario = comentario;
    }


    public List<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }
}
