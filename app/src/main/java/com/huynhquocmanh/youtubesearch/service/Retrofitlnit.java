package com.huynhquocmanh.youtubesearch.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitlnit {
    private  static Retrofit retrofit =null;
    public static Retrofit getclient(String base_url){
        OkHttpClient okHttpClient =new OkHttpClient.Builder()
                .readTimeout(9000, TimeUnit.MILLISECONDS)
                .connectTimeout(9000,TimeUnit.MILLISECONDS)
                .writeTimeout(10000,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
        Gson gson = new GsonBuilder().setLenient().create() ;
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
