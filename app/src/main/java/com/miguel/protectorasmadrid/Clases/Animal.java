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
    String nombre;
    String tamanio;
    String estado;
    String descripcionAnimal;
    String fechaNacimiento;
    String especie;
    String fechaEntrada;
    String genero;
    String ubicacion;

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    List<Bitmap>listaFotos;


    // CONSTRUCTORES

    public Animal(){
    }



    public Animal(Animal a) {
        this.idAnimal = a.idAnimal;
        this.protectora = a.protectora;
        this.nombre = a.nombre;
        this.tamanio = a.tamanio;
        this.estado = a.estado;
        this.descripcionAnimal = a.descripcionAnimal;
        this.fechaNacimiento = a.fechaNacimiento;
        this.especie = a.especie;
        this.fechaEntrada = a.fechaEntrada;
        this.listaFotos = a.listaFotos;
        this.ubicacion = a.ubicacion;
    }

    public int generarId(String nombre, int protectora){
        String id = nombre + protectora;
        return id.hashCode();
    }


    protected Animal(Parcel in) {
        idAnimal = in.readInt();
        protectora = in.readString();
        nombre = in.readString();
        tamanio = in.readString();
        estado = in.readString();
        descripcionAnimal = in.readString();
        especie = in.readString();
        listaFotos = in.createTypedArrayList(Bitmap.CREATOR);
    }


    // GETTERS Y SETTERS

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getProtectora() {
        return protectora;
    }

    public void setProtectora(String idProtectora) {
        this.protectora = idProtectora;
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

    public List<Bitmap> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<Bitmap> listaFotos) {
        this.listaFotos = listaFotos;
    }


    // PARCELABLE

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idAnimal);
        parcel.writeString(protectora);
        parcel.writeString(ubicacion);
        parcel.writeString(nombre);
        parcel.writeString(tamanio);
        parcel.writeString(estado);
        parcel.writeString(descripcionAnimal);
        parcel.writeString(especie);
        parcel.writeTypedList(listaFotos);
    }
}
