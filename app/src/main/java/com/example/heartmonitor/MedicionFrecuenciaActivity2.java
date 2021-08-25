package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MedicionFrecuenciaActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_medicion_frec);
    }

    public void toggleMedicionAuto(View view){
        ToggleButton toggle = findViewById(R.id.toggleMedicionAuto);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    System.out.println("Activó la medición automática");
                }
                else{
                    System.out.println("Desactivó la medición automática");
                }
            }
        });
    }
}