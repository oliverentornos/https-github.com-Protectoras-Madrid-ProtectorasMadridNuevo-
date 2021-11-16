package com.miguel.protectorasmadrid.Clases;

public class Noticia {

    private String foto;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String nomProtectora;

    public Noticia(String foto, String titulo, String descripcion, String fecha, String nomProtectora) {
        this.foto = foto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.nomProtectora = nomProtectora;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNomProtectora() {
        return nomProtectora;
    }

    public void setNomProtectora(String nomProtectora) {
        this.nomProtectora = nomProtectora;
    }
}
