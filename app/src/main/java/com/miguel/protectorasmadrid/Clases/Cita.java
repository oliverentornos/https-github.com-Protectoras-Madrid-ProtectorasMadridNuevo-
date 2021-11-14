package com.miguel.protectorasmadrid.Clases;

public class Cita {

    String fecha;
    String hora;
    String nombre;
    String nombreAnimal;
    String especie;
    int estado;

    public Cita(String fecha, String hora, String nombre, String animal, String especie,  int estado) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombre = nombre;
        this.nombreAnimal = animal;
        this.especie = especie;
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnimal() {
        return nombreAnimal;
    }

    public void setAnimal(String animal) {
        this.nombreAnimal = animal;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
