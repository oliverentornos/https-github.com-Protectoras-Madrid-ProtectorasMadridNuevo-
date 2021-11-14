package com.miguel.protectorasmadrid.Clases;

import java.io.Serializable;

public class Foto implements Serializable {
    int idFoto;
    int idAnimal;
    String foto;

    public Foto() {
    }

    public Foto(int idFoto, int idAnimal, String foto) {
        this.idFoto = idFoto;
        this.idAnimal = idAnimal;
        this.foto = foto;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
