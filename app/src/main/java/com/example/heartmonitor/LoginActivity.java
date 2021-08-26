package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heartmonitor.Interfaces.UsuarioResponse;
import com.example.heartmonitor.Models.Usuario;
import com.example.heartmonitor.Services.RetrofitResponseClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText txtUserName, txtPassword;
    Button btn_login;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        btn_login = findViewById(R.id.btnIniciarSesison);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = txtUserName.getText().toString();
                pass = txtPassword.getText().toString();
                chequear_login(user, pass);
            }
        });

    }

//    API - Volley
//    private void chequear_login(String user , String pass) {
//        String URL = "http://192.168.56.1:8000/webservice/inicio-sesion/";
//        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(), "respose: " + response, Toast.LENGTH_LONG).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println("Error de logeo");
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap param = new HashMap();
//                param.put("usuario", "HenryPM06");
//                param.put("clave", "123456");
//                return param;
//            }
//        };
//
//        Volley.newRequestQueue(this).add(request);
//    }

    public void chequear_login(String user, String pass){
        Retrofit retrofit = RetrofitResponseClient.getRetrofit();
        UsuarioResponse API = retrofit.create(UsuarioResponse.class);
        Map<String, String> param = new HashMap<>();
        param.put("usuario", user);
        param.put("clave", pass);
        Call<Usuario> call = API.LoginRespose(param);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
                    if(response.body().isResponse()){
                        Toast.makeText(getApplicationContext(), response.body().getMensaje(), Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), response.body().getMensaje(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Mensaje: Existio un error en la captura de datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mensaje: El servidor esta apagado...", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(comprobarConexionAInternet()){
            Toast.makeText(this.getApplicationContext(), "Conexión establecida a internet", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this.getApplicationContext(), "No hay conexion a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean comprobarConexionAInternet(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            System.out.println("Hay conexión a internet");
            return true;
        }
        else{
            System.out.println("NO Hay conexión a internet");
            return false;
        }
    }
}