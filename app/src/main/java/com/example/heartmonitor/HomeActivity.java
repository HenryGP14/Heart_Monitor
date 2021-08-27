package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences preferencia;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);

        preferencia = this.getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferencia.edit();

        if (revisar_sesion().equals("Familiar")) {
            Intent intent = new Intent(HomeActivity.this, ListaPacientesActivity.class);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();
        }
    }

    public String revisar_sesion() {
        return this.preferencia.getString("tipo", "");
    }

    public void btnIrConfig_actHome(View view){
        Intent intent = new Intent(this, ConfiguracionActivity.class);
        startActivity(intent);
    }

    public void btnIrAEscaneoPulso(View view){
        Toast.makeText(this, "Ir a la ventana de escaneo de pulso", Toast.LENGTH_SHORT).show();
    }

    public void btnIrASaludCardiaca(View view){
        Intent intent = new Intent(this, SaludCardiacaActivity.class);
        startActivity(intent);
    }
}