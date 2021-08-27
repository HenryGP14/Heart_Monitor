package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.heartmonitor.Interfaces.UsuarioResponse;
import com.example.heartmonitor.Models.Usuario;
import com.example.heartmonitor.Services.RetrofitResponseClient;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrarPacienteActivity extends AppCompatActivity {

    private EditText txtFecha;
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtUsuario;
    private EditText txtClave, txtClaveConf;
    private EditText txtCorreo;
    private RadioButton F, M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_registrar_paciente);

        txtFecha = findViewById(R.id.txtFecha);
        txtUsuario = findViewById(R.id.txtUsuario_Paciente);
        txtNombres = findViewById(R.id.txtNombres_Paciente);
        txtApellidos = findViewById(R.id.txtApellidos_Paciente);
        txtClave = findViewById(R.id.txtClave_Paciente);
        txtClaveConf = findViewById(R.id.txtClaveConf_Paciente);
        txtCorreo = findViewById(R.id.txtCorreo_Paciente);
        F = findViewById(R.id.rbFemenino_Paciente);
        M = findViewById(R.id.rbMasculino_Paciente);

        txtFecha.addTextChangedListener(text_changue);
    }

    public void btnRegistrar_onClick(View view) {
        String genero = "";
        if (txtClave.getText().toString().equals(txtClaveConf.getText().toString())) {
            String[] sl = txtFecha.getText().toString().toString().split("/");
            if (sl.length > 1) {
                String fecha = sl[2] + "-" + sl[1] + "-" + sl[0];
                if (M.isChecked())
                    genero = "M";
                else
                    genero = "F";
                Retrofit retrofit = RetrofitResponseClient.getRetrofit();
                UsuarioResponse API = retrofit.create(UsuarioResponse.class);
                Map<String, String> param = new HashMap<>();
                param.put("usuario", txtUsuario.getText().toString());
                param.put("clave", txtClave.getText().toString());
                param.put("nombres", txtNombres.getText().toString());
                param.put("apellidos", txtApellidos.getText().toString());
                param.put("fecha_nacimiento", fecha);
                param.put("genero", genero);
                param.put("correo", txtCorreo.getText().toString());

                Call<Usuario> call = API.AddPacienteResponse(param);

                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isResponse()) {
                                Intent intent = new Intent(RegistrarPacienteActivity.this, LoginActivity.class);
                                Toast.makeText(getApplicationContext(), response.body().getMensaje() + ", puedes iniciar sesión.", Toast.LENGTH_LONG).show();
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), response.body().getMensaje(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Mensaje: Existió un error en el envio de datos", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Mensaje: El servidor esta apagado...", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Debe poner una fecha valida", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "La contraseña no coinciden, intentalo nuevamente..", Toast.LENGTH_LONG).show();
        }

    }

    public void btnAtras_onClick(View view){
        onBackPressed();
        finish();
    }

    public void btnCancelar_onClick(View view){
        onBackPressed();
        finish();
    }

    TextWatcher text_changue = new TextWatcher() {
        private String current = "";
        private String ddmmyyyy = "DDMMYYYY";
        private Calendar cal = Calendar.getInstance();

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (! s.toString().equals(current)) {
                String clean = s.toString().replaceAll("[^\\d.]", "");
                String cleanC = current.replaceAll("[^\\d.]", "");

                int cl = clean.length();
                int sel = cl;
                for (int i = 2; i <= cl && i < 6; i += 2) {
                    sel++;
                }
                //Fix for pressing delete next to a forward slash
                if (clean.equals(cleanC)) sel--;

                if (clean.length() < 8) {
                    clean = clean + ddmmyyyy.substring(clean.length());
                } else {
                    //This part makes sure that when we finish entering numbers
                    //the date is correct, fixing it otherwise
                    int day = Integer.parseInt(clean.substring(0, 2));
                    int mon = Integer.parseInt(clean.substring(2, 4));
                    int year = Integer.parseInt(clean.substring(4, 8));

                    if (mon > 12) mon = 12;
                    cal.set(Calendar.MONTH, mon - 1);

                    year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                    cal.set(Calendar.YEAR, year);
                    // ^ first set year for the line below to work correctly
                    //with leap years - otherwise, date e.g. 29/02/2012
                    //would be automatically corrected to 28/02/2012

                    day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                    clean = String.format("%02d%02d%02d", day, mon, year);
                }

                clean = String.format("%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8));

                sel = sel < 0 ? 0 : sel;
                current = clean;
                txtFecha.setText(current);
                txtFecha.setSelection(sel < current.length() ? sel : current.length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
    };
}