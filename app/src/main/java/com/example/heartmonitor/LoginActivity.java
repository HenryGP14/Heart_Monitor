package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    EditText txtUserName, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
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