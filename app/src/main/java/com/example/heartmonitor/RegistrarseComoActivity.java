package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegistrarseComoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_registrarse_como);

        Button btn_paciente = findViewById(R.id.btn_registrarPaciente);
        Button btn_familiar = findViewById(R.id.btn_registrarFamiliar);
        ImageButton btn_atras = findViewById(R.id.btnAtras_actRegisComo);

        btn_paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarseComoActivity.this, RegistrarPacienteActivity.class);
                startActivity(intent);
            }
        });

        btn_familiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarseComoActivity.this, RegistrarFamiliarActivity.class);
                startActivity(intent);
            }
        });

        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}