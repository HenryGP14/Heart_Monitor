package com.example.heartmonitor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InformacionUsuarioActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private SharedPreferences preferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_info_usuario);

        preferencia = this.getSharedPreferences("Sesiones",Context.MODE_PRIVATE);
        builder = new AlertDialog.Builder(this);
    }

    public void btnIrAtras_actInfoUsu(View view){
        this.finish();
    }

    public void ontouchFotoPerfil(View view){
        builder.setMessage("¿Desea cambiar la imagen de perfil?");
        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Este método falta por implementar", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", null);
    }

    public void ontouchNombres(View view){
        EditText nombres = new EditText(this);
        nombres.setInputType(InputType.TYPE_CLASS_TEXT);

        builder.setMessage("Cambiar nombres del paciente");
        builder.setView(nombres);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Camcelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ontouchGenero(View view){
        spinner = new Spinner(this);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.cambiarGenero_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        builder.setMessage("Cambiar género");
        builder.setView(spinner);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ontouchFechaNacimiento(View view){
        EditText fecha = new EditText(this);
        fecha.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        fecha.setText("01/01/2000");

        builder.setMessage("Cambiar fecha de nacimiento");
        builder.setView(fecha);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ontouchCorreo(View view){
        EditText email = new EditText(this);
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        email.setText("ejemplo@gmail.com");

        builder.setMessage("Cambiar correo electrónico");
        builder.setView(email);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ontouchNombreUsuario(View view){
        EditText usuario = new EditText(this);
        usuario.setInputType(InputType.TYPE_CLASS_TEXT);
        usuario.setText( this.preferencia.getString("usuario", "") );

        builder.setMessage("Cambiar nombre de usuario");
        builder.setView(usuario);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ontouchContrasegna(View view){

    }

    public void ontouchGuardarInfoUsuario(View view){
        builder.setMessage("Se actualizaron los datos correctamente");
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}