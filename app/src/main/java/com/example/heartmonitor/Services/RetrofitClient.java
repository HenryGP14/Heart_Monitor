package com.example.heartmonitor.Services;

import static retrofit2.converter.gson.GsonConverterFactory.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String URL = "http://127.0.0.1:8000";
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
