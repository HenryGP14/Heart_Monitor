package com.example.heartmonitor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class InformacionUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_info_usuario);
    }

    public void btnIrAtras_actInfoUsu(View view){
        this.finish();
    }

    public void ontouchFotoPerfil(View view){

    }

    public void ontouchNombres(View view){
        EditText nombres = new EditText(this);
        nombres.setInputType(InputType.TYPE_CLASS_TEXT);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Cambiar nombres del paciente");
        builder.setView(nombres);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Camcelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ontouchGenero(View view){

    }

    public void ontouchFechaNacimiento(View view){

    }

    public void ontouchCorreo(View view){

    }

    public void ontouchNombreUsuario(View view){

    }

    public void ontouchContrasegna(View view){

    }

    public void ontouchGuardarInfoUsuario(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Se actualizaron los datos correctamente");
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}