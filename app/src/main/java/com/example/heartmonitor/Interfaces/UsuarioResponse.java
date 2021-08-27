package com.example.heartmonitor.Interfaces;

import com.example.heartmonitor.Models.Familiar;
import com.example.heartmonitor.Models.Usuario;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface UsuarioResponse {
    @FormUrlEncoded
    @POST("inicio-sesion/")
    Call<Usuario> LoginRespose(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST("familiar/")
    Call<Usuario> AddFamiliarResponse(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @PUT("familiar/")
    Call<Usuario> EditFamiliarResponse(@Body Usuario familiar);

    @FormUrlEncoded
    @POST("paciente/")
    Call<Usuario> AddPacienteResponse(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @PUT("paciente/")
    Call<Usuario> EditPacienteResponse(@FieldMap Map<String, String> param);

    @GET("paciente/")
    Call<Usuario> GetPaciente(@QueryMap Map<String, String> param);

}
