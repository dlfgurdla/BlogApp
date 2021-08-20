package com.com.blog.service;

import com.com.blog.config.SessionInterceptor;
import com.com.blog.controller.dto.CMRespDto;
import com.com.blog.controller.dto.PostUpdateDto;
import com.com.blog.model.Post;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

//인증이 필요없는 주소 /auth
//인증이 필요한 주소 /user, /post
public interface PostService {

    @POST("/post/{id}")
    Call<CMRespDto<Post>> insert(@Path("id") int id,@Body PostUpdateDto postUpdateDto);

    @PUT("/post/{id}")
    Call<CMRespDto<Post>> update(@Path("id") int id, @Body PostUpdateDto postUpdateDto);

    @DELETE("/post/{id}") // 추후 주소 변경 필요 : /post
    Call<CMRespDto> deleteById(@Path("id") int id);

    //@Path("id") 이거 적어 줘야됨

    @GET("/post/{id}") // 추후 주소 변경 필요 : /post
    Call<CMRespDto<Post>> findById(@Path("id") int id);


    @GET("/post") // 추후 주소 변경 필요 : /post
    Call<CMRespDto<List<Post>>> findAll();


    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new SessionInterceptor()).build();

    //test
    @GET("/init/post")
    Call<CMRespDto<List<Post>>> initPost();

    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://180.64.118.133:8080")
            .build();



    PostService service = retrofit.create(PostService.class);
}
