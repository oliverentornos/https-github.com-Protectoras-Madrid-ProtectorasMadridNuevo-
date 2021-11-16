package com.miguel.protectorasmadrid.Api;




import com.miguel.protectorasmadrid.Clases.Animal;
import com.miguel.protectorasmadrid.Clases.Cita;
import com.miguel.protectorasmadrid.Clases.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioApi {


    @FormUrlEncoded
    @POST("/login")
    Call<Usuario> doLogin(
            @Field("nickname") String usuario,
            @Field("passwd") String contrasena
    );

    @FormUrlEncoded
    @POST("/fetch/usuario")
    Call<Usuario> fetchClient(@Field("idUsuario") int idUsuario
    );

    @FormUrlEncoded
    @POST("/animalesFav")
    Call<List<Integer>> getAnimalesFav(@Field("idUsuario") int idUsuario
    );

    @FormUrlEncoded
    @POST("/eliminarFav")
    Call<Void> eliminarFav(@Field("idUsuario") int idUsuario,
                            @Field("idAnimal") int idAnimal
    );

    @FormUrlEncoded
    @POST("/anadirFav")
    Call<Void> insertFav(@Field("idUsuario") int idUsuario,
                            @Field("idAnimal") int idAnimal
    );


    @GET("/citasUsuario/{idUsuario}")
    Call<ArrayList<Cita>> getCitas(@Path("idUsuario") int idUsuario);


    @FormUrlEncoded
    @POST("/registerUser")
    Call<Void> registrarUsuario(
            @Field("idUsuario") int idUsuario,
            @Field("nombre") String nombre,
            @Field("ape1") String ape1,
            @Field("ape2") String ape2,
            @Field("telefono") String telefono,
	        @Field("nickname") String nickname,
            @Field("passwd") String passwd,
            @Field("imagen") String imagen);

    @FormUrlEncoded
    @POST("/checkUsername")
    Call<String> checkUsername(
            @Field("usuario") String usuario
    );


    @FormUrlEncoded
    @POST("/update/password")
    Call<Void> updatePassword(
            @Field("passwd") String passwd,
            @Field("idUsuario") int idUsuario);

}
