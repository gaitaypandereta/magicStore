package com.example.magistore.modelo;

import java.io.Serializable;
import java.util.List;

public class Megusta implements Serializable {
    private List<Usuario> user;

    public Megusta(List<Usuario> user) {
        this.user = user;
    }

    public List<Usuario> getUser() {
        return user;
    }

    public void setUser(List<Usuario> user) {
        this.user = user;
    }
}
