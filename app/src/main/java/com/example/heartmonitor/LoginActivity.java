package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heartmonitor.Interfaces.UserInterface;
import com.example.heartmonitor.Services.RetrofitClient;

import java.io.IOException;

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
                String token = createToken(user, pass);
                chequear_login(token);
            }
        });

    }

    private void chequear_login(String token) {
//        Retrofit retrofit = RetrofitClient.getRetrofit();
//        UserInterface api = retrofit.create(UserInterface.class);
//        Call<String> call = api.check_login(token);
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()){
//                    if (response.body().matches("Usuario no existe")){
//                        Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_LONG).show();
//                    }
//                }else{
//
//                }
//            }
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                System.out.println("Error de llamada");
//            }
//        });
        setContentView(R.layout.act_home);
    }

    private String createToken(String user, String pass) {
        byte[] data = new byte[0];
        try {
            data = (user + ":" + pass).getBytes("UTF-8");
        }catch (Exception e){
            System.out.println("Error de: " + e.getMessage());
        }
        return  "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
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