package com.example.heartmonitor.Interfaces;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserInterface {
    @POST("webservice/inicio-sesion")
    Call<String> check_login(@Header("token") String token);
}
