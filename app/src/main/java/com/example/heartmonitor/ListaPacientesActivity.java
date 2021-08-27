package com.example.heartmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heartmonitor.Interfaces.UsuarioResponse;
import com.example.heartmonitor.Models.Usuario;
import com.example.heartmonitor.Services.RetrofitResponseClient;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListaPacientesActivity extends AppCompatActivity {

    private SharedPreferences preferencia;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lista_pacientes);

        preferencia = this.getSharedPreferences("Sesiones", Context.MODE_PRIVATE);
        editor = preferencia.edit();

        Retrofit retrofit = RetrofitResponseClient.getRetrofit();
        UsuarioResponse API = retrofit.create(UsuarioResponse.class);

        Map<String, Integer> param = new HashMap<>();
        param.put("id", this.preferencia.getInt("idUsuario", 0));

        Call<Usuario> call = API.GetFamiliar(param);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    if (response.body().isResponse()) {
                        TextView nom = findViewById(R.id.txtNombreP);
                        TextView ape = findViewById(R.id.txtGeneroP);

                        nom.setText(preferencia.getString("nombres", ""));
                        ape.setText(preferencia.getString("genero", ""));
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

    public void btnIrAtras_actListaPaci(View view){
        editor.remove("logeado");
        editor.remove("idUsuario");
        editor.remove("nombres");
        editor.remove("apellidos");
        editor.remove("tipo");
        editor.apply();
        Intent intent = new Intent(ListaPacientesActivity.this, LoginActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }
}