package com.miguel.protectorasmadrid.Api;

import com.miguel.protectorasmadrid.Clases.Cita;
import com.miguel.protectorasmadrid.Clases.Protectora;

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


}
