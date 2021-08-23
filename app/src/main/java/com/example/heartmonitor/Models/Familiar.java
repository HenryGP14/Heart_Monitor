package com.example.heartmonitor.Models;

import com.google.gson.annotations.SerializedName;

public class Familiar {
    private int id;
    private String tipo_cuenta;
    private String nombres;
    private String apelldos;
    private String ruta_foto;
    private String genero;
    private String celular;

    public int getId() {
        return id;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApelldos() {
        return apelldos;
    }

    public String getRuta_foto() {
        return ruta_foto;
    }

    public String getGenero() {
        return genero;
    }

    public String getCelular() {
        return celular;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    private Paciente paciente;
}
