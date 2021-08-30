package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ConfiguracionActivity extends AppCompatActivity {

    private SharedPreferences preferencia;
    private SharedPreferences.Editor editor;
    private TextView txtNombresAP, txtDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_configuracion);

        preferencia = this.getSharedPreferences("Sesiones",Context.MODE_PRIVATE);
        editor = preferencia.edit();

        txtNombresAP = findViewById(R.id.txtNombresAP_Config);
        txtDatos = findViewById(R.id.txtEdad);

        String nombres = this.preferencia.getString("nombres", "");
        String apellidos = this.preferencia.getString("apellidos", "");
        String fecha = this.preferencia.getString("fecha_nacimiento","2000-16-20");
        String genero = this.preferencia.getString("genero","");

        String[] sp = fecha.split("-");
        Calendar fechaNacimiento = new GregorianCalendar(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]), Integer.parseInt(sp[2]));
        Calendar ahora = Calendar.getInstance();
        long edadEnDias = (ahora.getTimeInMillis() - fechaNacimiento.getTimeInMillis())
                / 1000 / 60 / 60 / 24;
        int anos = Double.valueOf(edadEnDias / 365.25d).intValue();

        txtNombresAP.setText(nombres + " " + apellidos);
        txtDatos.setText(genero + ", " + anos + " años");

    }

    public void btnAtras_actConf(View view) {
        this.finish();
    }

    public void btnIrBuscarDispositivo(View view) {
        Intent puente = new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(puente);
    }

    public void btnIrInformUsuario(View view) {
        Intent puente = new Intent(getApplicationContext(), InformacionUsuarioActivity.class);
        startActivity(puente);
    }

    public void btnIrContactosMonitorean(View view) {
        Intent puente = new Intent(getApplicationContext(), ContactosActivity.class);
        startActivity(puente);
    }

    public void btnIrMedicionAutomatica(View view) {
        Intent puente = new Intent(getApplicationContext(), MedicionFrecuenciaActivity2.class);
        startActivity(puente);
    }

    public void btnCerrarSesion(View view) {
        editor.remove("logeado");
        editor.remove("idUsuario");
        editor.remove("nombres");
        editor.remove("apellidos");
        editor.remove("tipo");
        editor.apply();
        Intent intent = new Intent(ConfiguracionActivity.this, LoginActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }
}