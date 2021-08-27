package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.heartmonitor.Interfaces.UsuarioResponse;
import com.example.heartmonitor.Models.Usuario;
import com.example.heartmonitor.Services.RetrofitResponseClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrarFamiliarActivity extends AppCompatActivity {

    private String usuario_paciente;
    private EditText busq_usuario;
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtUsuario;
    private EditText txtClave, txtClaveConf;
    private EditText txtCelular;
    private RadioButton F, M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_registrar_familiar);

        busq_usuario = findViewById(R.id.txtBuscarUsuario_actRegisFam);
        txtNombres = findViewById(R.id.txtNombre_Familiar);
        txtApellidos = findViewById(R.id.txtApellidos_Familiar);
        txtCelular = findViewById(R.id.txtCelular_Familiar);
        txtUsuario = findViewById(R.id.txtUsuario_Familiar);
        txtClave = findViewById(R.id.txtClave_Familiar);
        txtClaveConf = findViewById(R.id.txtClaveConf_Familiar);
        F = findViewById(R.id.rbFemenino_Familiar);
        M = findViewById(R.id.rbMasculino_Familiar);
    }

    public void btnRegistrar_onClick(View view){
        String genero = "";
        if(txtClave.getText().toString().equals(txtClaveConf.getText().toString())) {
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
            param.put("genero", genero);
            param.put("celular", txtCelular.getText().toString());
            param.put("user_paciente", busq_usuario.getText().toString());

            Call<Usuario> call = API.AddFamiliarResponse(param);

            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        if (response.body().isResponse()) {
                            Toast.makeText(getApplicationContext(), response.body().getMensaje(), Toast.LENGTH_LONG).show();
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
        }else{
            Toast.makeText(getApplicationContext(), "La contraseña no coinciden, intentalo nuevamente..", Toast.LENGTH_LONG).show();
        }
    }

    public void chequearUsuario_onClick(View view) {
        Retrofit retrofit = RetrofitResponseClient.getRetrofit();
        UsuarioResponse API = retrofit.create(UsuarioResponse.class);
        usuario_paciente = busq_usuario.getText().toString();
        Map<String, String> param = new HashMap<>();
        param.put("username", usuario_paciente);
        Call<Usuario> call = API.GetPaciente(param);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (response.body().isResponse()) {
                        String nombre_completos = response.body().getPaciente().getNombres() + " " + response.body().getPaciente().getApellidos();
                        Toast.makeText(getApplicationContext(), "El paciente a registrar es: " + nombre_completos, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMensaje(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Mensaje: Existió un error en la captura de datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mensaje: El servidor esta apagado...", Toast.LENGTH_LONG).show();
            }
        });
    }


}