package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MedicionFrecuenciaActivity2 extends AppCompatActivity {

    AlertDialog.Builder builder;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_medicion_frec);

        toggleMedicionAuto();
        builder = new AlertDialog.Builder(this);
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
        EditText horaInicio = new EditText(this);
        horaInicio.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);

        builder.setMessage("Hora de inicio");
        builder.setView(horaInicio);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void btnHoraFinalizacion(View view){
        EditText horaFin = new EditText(this);
        horaFin.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);

        builder.setMessage("Hora de finalización");
        builder.setView(horaFin);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void btnRepetirCiclo(View view){
        spinner = new Spinner(this);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.repetirCiclo_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        builder.setMessage("Repetir ciclo");
        builder.setView(spinner);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void btnGuardarMedicionAutom(View vie){
        builder.setMessage("Falta implementar este botón");
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}