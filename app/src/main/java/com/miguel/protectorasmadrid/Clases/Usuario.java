package com.miguel.protectorasmadrid.Clases;

import java.util.List;

public class Usuario {


    int idUsuario;
    String nombre;
    String ape1;
    String ape2;
    String telefono;
    String passwd;
    List<Animal>listAnimalFav;
    String nickname;
    String imagen;


    public Usuario( String nombre, String ape1, String ape2, String telefono, String passwd, String nickname, String imagen) {
        this.idUsuario = generarId();
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.telefono = telefono;
        this.passwd = passwd;
        this.nickname = nickname;
        this.imagen = imagen;
    }

    public Usuario(Usuario usuarioCopia) {
        this.idUsuario = usuarioCopia.idUsuario;
        this.nombre = usuarioCopia.nombre;
        this.nickname = usuarioCopia.nickname;
        this.ape1 = usuarioCopia.ape1;
        this.ape2 = usuarioCopia.ape2;
        this.telefono = usuarioCopia.telefono;
        this.passwd = usuarioCopia.passwd;
        this.listAnimalFav = usuarioCopia.listAnimalFav;
    }

    public Usuario() {
    }



    private int generarId(){
        String id = this.nickname+this.passwd;
        return id.hashCode();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
