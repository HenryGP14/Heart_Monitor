package com.example.heartmonitor.Interfaces;

import com.example.heartmonitor.Models.Familiar;
import com.example.heartmonitor.Models.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UsuarioResponse {
    @FormUrlEncoded
    @POST("inicio-sesion/")
    Call<Usuario> LoginRespose(@FieldMap Map<String, String> param);
}
