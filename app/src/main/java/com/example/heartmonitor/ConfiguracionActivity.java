package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfiguracionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_configuracion);
    }

    public void btnAtras_actConf(View view){
        this.finish();
    }

    public void btnIrBuscarDispositivo(View view){
        /*Intent puente = new Intent(getApplicationContext(), SaludCardiacaActivity.class);
        startActivity(puente);*/
    }

    public void btnIrInformUsuario(View view){
        Intent puente = new Intent(getApplicationContext(), InformacionUsuarioActivity.class);
        startActivity(puente);
    }

    public void btnIrContactosMonitorean(View view){
        Intent puente = new Intent(getApplicationContext(), ContactosActivity.class);
        startActivity(puente);
    }

    public void btnIrMedicionAutomatica(View view){
        Intent puente = new Intent(getApplicationContext(), MedicionFrecuenciaActivity2.class);
        startActivity(puente);
    }

    public void btnCerrarSesion(View view){
        this.finish();
    }
}