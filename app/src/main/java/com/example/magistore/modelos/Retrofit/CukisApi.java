package com.example.magistore.modelos.Retrofit;

import com.example.magistore.modelos.Cuki;
import com.example.magistore.modelos.Post;
import com.example.magistore.modelos.Usuario;
import com.squareup.okhttp.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CukisApi {

        @GET("cukis/{id}/")
        Call<Cuki> getCuki(@Path("id") String id);

        @POST("/usuario")
        Call<Usuario> postUsuario(@Body Usuario usuario);

        @PUT("usuario/{id}")
        Call<ResponseBody> updateUser(@Path("id") String id, @Body Usuario user);


}
