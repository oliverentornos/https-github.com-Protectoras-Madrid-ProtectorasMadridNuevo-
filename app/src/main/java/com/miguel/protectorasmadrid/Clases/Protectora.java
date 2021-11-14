package com.miguel.protectorasmadrid.Clases;

import android.graphics.Bitmap;

public class Protectora {


    int idProtectora;
    String telefono;
    String nombre;
    String email;
    String ubicacion;
    String passwd;
    //BASE64
    String imagen;
    Bitmap imagenLogin;


    public Protectora() {
    }

    public Protectora(int idProtectora, String telefono, String nombre, String email, String ubicacion, String passwd, String imagen) {
        this.idProtectora = idProtectora;
        this.telefono = telefono;
        this.nombre = nombre;
        this.email = email;
        this.ubicacion = ubicacion;
        this.passwd = passwd;
        this.imagen = imagen;
    }

    public int getidProtectora() {
        return idProtectora;
    }

    public void setidProtectora(int idProtectora) {
        this.idProtectora = idProtectora;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenLogin() {
        return imagenLogin;
    }

    public void setImagenLogin(Bitmap imagenLogin) {
        this.imagenLogin = imagenLogin;
    }
}
