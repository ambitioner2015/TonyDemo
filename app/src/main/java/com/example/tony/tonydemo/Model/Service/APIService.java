package com.example.tony.tonydemo.Model.Service;

import com.example.tony.tonydemo.Model.HttpResult;
import com.example.tony.tonydemo.Model.Entity.LoginEntity;
import com.example.tony.tonydemo.Model.Entity.NewsEntity;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lzy on 2016/7/30.
 */
public interface APIService {
    @FormUrlEncoded
    @POST("account/SignIn")
    Observable<LoginEntity> Login(@Field("moblie") String moblie, @Field("password") String password);

    @GET("social")
    Observable<HttpResult<List<NewsEntity>>> GetSocialNews(@Query("key") String key, @Query("page") int page, @Query("num") int num);

    @GET("huabian")
    Observable<HttpResult<List<NewsEntity>>> GetFunnyNews(@Query("key") String key, @Query("page") int page, @Query("num") int num);

    @GET("tiyu")
    Observable<HttpResult<List<NewsEntity>>> GetSportNews(@Query("key") String key, @Query("page") int page, @Query("num") int num);

    @GET("health")
    Observable<HttpResult<List<NewsEntity>>> GetHealthNews(@Query("key") String key, @Query("page") int page, @Query("num") int num);

    @GET("meinv")
    Observable<HttpResult<List<NewsEntity>>> GetBeautyImage(@Query("key") String key, @Query("page") int page, @Query("num") int num);


    //微信热门精选
//    @FormUrlEncoded
//    @GET("News/GetSocialNews")
//    Observable<HttpResult<List<NewsEntity>>> Getwxhot(@Field("page") String page, @Field("size") String size);
}
