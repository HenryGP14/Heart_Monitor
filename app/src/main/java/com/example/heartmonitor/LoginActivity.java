package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
    }

    public void clickIniciarSesion(View view){
        /* Consumir el web services para loguearse */
        Intent puente = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(puente);
    }

    public void clickRegistrarse(View view){
        /* Consumir el web services para registrarse */
    }
}