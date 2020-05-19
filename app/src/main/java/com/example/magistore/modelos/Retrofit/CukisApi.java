package com.example.magistore.modelos.Retrofit;

import com.example.magistore.modelos.Cuki;
import com.example.magistore.modelos.Post;
import com.example.magistore.modelos.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CukisApi {

        @GET("cukis/{id}/")
        Call<Cuki> getCuki(@Path("id") String id);

        @POST("/usuario")
        Call<Usuario> postUsuario(@Body Usuario usuario);


        @POST("/usuario")
        @FormUrlEncoded
        Call<Post> savePost(@Field("id") String id,
                            @Field("face") String face,
                            @Field("twt") String twt);

}
