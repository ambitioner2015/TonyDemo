package com.example.tony.tonydemo.Model.Service;

import com.example.tony.tonydemo.Model.Entity.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lzy on 2016/7/30.
 */
public interface MovieService {

    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
