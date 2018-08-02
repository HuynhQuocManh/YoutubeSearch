package com.huynhquocmanh.youtubesearch.service;



import com.huynhquocmanh.youtubesearch.Model.ModelData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataAPI {
    @GET("search")
    Call<ModelData> getResurt(@Query("part") String part,
                              @Query("q") String q,
                              @Query("maxResults") String maxResults,
                              @Query("type") String type,
                              @Query("key") String key);

//https://www.googleapis.com/youtube/v3/search/20/data
//    @GET("search/{id}/data")
//    Call<Object>getTrang(@Path("id")String id);
}
