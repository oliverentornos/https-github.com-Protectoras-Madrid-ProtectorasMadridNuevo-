package com.miguel.protectorasmadrid.Clases;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;

import java.util.Date;
import java.util.List;

public class Animal implements Parcelable {

    int idAnimal;
    String protectora;
    int idProtectora;
    String nombre;
    String tamanio;
    String estado;
    String descripcionAnimal;
    String fechaNacimiento;
    String especie;
    String fechaEntrada;
    String genero;
    List<Bitmap>listaFotos;


    // CONSTRUCTORES

    public Animal(){
    }


    protected Animal(Parcel in) {
        idAnimal = in.readInt();
        protectora = in.readString();
        idProtectora = in.readInt();
        nombre = in.readString();
        tamanio = in.readString();
        estado = in.readString();
        descripcionAnimal = in.readString();
        fechaNacimiento = in.readString();
        especie = in.readString();
        fechaEntrada = in.readString();
        genero = in.readString();
        listaFotos = in.createTypedArrayList(Bitmap.CREATOR);
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public int generarId(String nombre, int protectora){
        String id = nombre + protectora;
        return id.hashCode();
    }

    public Animal(int idAnimal, String protectora, int idProtectora, String nombre, String tamanio, String estado, String descripcionAnimal, String fechaNacimiento, String especie, String fechaEntrada, String genero, List<Bitmap> listaFotos) {
        this.idAnimal = idAnimal;
        this.protectora = protectora;
        this.idProtectora = idProtectora;
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.estado = estado;
        this.descripcionAnimal = descripcionAnimal;
        this.fechaNacimiento = fechaNacimiento;
        this.especie = especie;
        this.fechaEntrada = fechaEntrada;
        this.genero = genero;
        this.listaFotos = listaFotos;
    }


// PARCELABLE


    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getProtectora() {
        return protectora;
    }

    public void setProtectora(String protectora) {
        this.protectora = protectora;
    }

    public int getIdProtectora() {
        return idProtectora;
    }

    public void setIdProtectora(int idProtectora) {
        this.idProtectora = idProtectora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionAnimal() {
        return descripcionAnimal;
    }

    public void setDescripcionAnimal(String descripcionAnimal) {
        this.descripcionAnimal = descripcionAnimal;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Bitmap> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<Bitmap> listaFotos) {
        this.listaFotos = listaFotos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idAnimal);
        parcel.writeString(protectora);
        parcel.writeInt(idProtectora);
        parcel.writeString(nombre);
        parcel.writeString(tamanio);
        parcel.writeString(estado);
        parcel.writeString(descripcionAnimal);
        parcel.writeString(fechaNacimiento);
        parcel.writeString(especie);
        parcel.writeString(fechaEntrada);
        parcel.writeString(genero);
        parcel.writeTypedList(listaFotos);
    }
}
