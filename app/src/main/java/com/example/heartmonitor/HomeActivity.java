package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
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