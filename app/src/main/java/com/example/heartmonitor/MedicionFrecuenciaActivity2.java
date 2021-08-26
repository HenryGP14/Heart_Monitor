package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MedicionFrecuenciaActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_medicion_frec);

        toggleMedicionAuto();
    }

    public void toggleMedicionAuto(){
        ToggleButton toggle = findViewById(R.id.toggleMedicionAuto);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Activó la medición automática", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Desactivó la medición automática", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void btnIrAtras_actMedicionFrec(View view){
        this.finish();
    }

    public void btnHoraInicio(View view){

    }

    public void btnHoraFinalizacion(View view){

    }

    public void btnRepetirCiclo(View view){

    }

    public void btnGuardarMedicionAutom(View vie){

    }
}