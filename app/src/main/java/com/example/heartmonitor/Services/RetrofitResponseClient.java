package com.example.heartmonitor.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitResponseClient {
    private static String URL = "http://172.16.31.101:8000";
    //private static String URL = "http://192.168.56.1:8000";
    private static String Enlase = "/webservice/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL + Enlase)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
