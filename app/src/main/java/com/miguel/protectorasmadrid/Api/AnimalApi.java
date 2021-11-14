package com.miguel.protectorasmadrid.Api;




import com.miguel.protectorasmadrid.Clases.Animal;
import com.miguel.protectorasmadrid.Clases.Foto;
import com.miguel.protectorasmadrid.Clases.PortadaAnimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AnimalApi {

    @GET("/animales")
    Call<ArrayList<PortadaAnimal>> getAnimales();


    @GET("/fotosAnimal/{idAnimal}")
    Call<List<Foto>> getImagenesAnimal(@Path("idAnimal") int idAnimal);


    @GET("/animalId/{idAnimal}")
    Call<Animal> getAnimalId(@Path("idAnimal") int idAnimal);


    @FormUrlEncoded
    @POST("/insertFotos")
    Call<Void> insertarFoto(
            @Field("idAnimal") int idAnimal,
            @Field("foto") String foto);


    @FormUrlEncoded
    @POST("/registerAnimal")
    Call<Void> insertarAnimal(
            @Field("idAnimal") int idAnimal,
            @Field("idProtectora") int idProtectora,
            @Field("nombre") String nombre,
            @Field("tamanio") String tamanio,
            @Field("estado") String estado,
            @Field("descripcionAnimal") String descripcionAnimal,
            @Field("fechaNacimiento") String fechaNacimiento,
            @Field("especie") String especie,
            @Field("fechaEntrada") String fechaEntrada,
            @Field("genero") String genero);







}
