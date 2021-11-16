package com.miguel.protectorasmadrid.Api;

import com.miguel.protectorasmadrid.Clases.Cita;
import com.miguel.protectorasmadrid.Clases.Protectora;
import com.miguel.protectorasmadrid.Clases.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProtectoraApi {


    @FormUrlEncoded
    @POST("/loginProtectora")
    Call<Protectora> doLoginPro(
            @Field("email") String email,
            @Field("passwd") String contrasena
    );


    @GET("/citasProtectora/{idProtectora}")
    Call<ArrayList<Cita>> getCitas(@Path("idProtectora") int idProtectora);

    @FormUrlEncoded
    @POST("/fetch/protectora")
    Call<Protectora> fetchProtectora(@Field("idProtectora") int idProtectora
    );

    @FormUrlEncoded
    @POST("/citasProtectora")
    Call<ArrayList<String>> citasProtectora(
            @Field("idProtectora") int idProtectora,
            @Field("fecha") String fecha);


    @FormUrlEncoded
    @POST("/insertCita")
    Call<Void> insertCita(
            @Field("idAnimal") int idAnimal,
            @Field("idProtectora") int idProtectora,
            @Field("idUsuario") int idUsuario,
            @Field("fecha") String fecha,
            @Field("hora") String hora);


}
