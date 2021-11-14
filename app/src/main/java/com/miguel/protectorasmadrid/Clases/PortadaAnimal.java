package com.miguel.protectorasmadrid.Clases;

public class PortadaAnimal  {

    int idAnimal;
    String nombre;
    String descripcionAnimal;
    String FOTO;

    public PortadaAnimal(int IDANIMAL, String NOMBRE, String DESCRIPCIONANIMAL, String FOTO) {
        this.idAnimal = IDANIMAL;
        this.nombre = NOMBRE;
        this.descripcionAnimal = DESCRIPCIONANIMAL;
        this.FOTO = FOTO;
    }

    public int getIDANIMAL() {
        return idAnimal;
    }

    public void setIDANIMAL(int IDANIMAL) {
        this.idAnimal = IDANIMAL;
    }

    public String getNOMBRE() {
        return nombre;
    }

    public void setNOMBRE(String nombre) {
        this.nombre = nombre;
    }

    public String getDESCRIPCIONANIMAL() {
        return descripcionAnimal;
    }

    public void setDESCRIPCIONANIMAL(String DESCRIPCIONANIMAL) {
        this.descripcionAnimal = DESCRIPCIONANIMAL;
    }

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }
}
