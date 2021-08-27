package com.example.heartmonitor.Models;

import com.google.gson.annotations.SerializedName;

public class Familiar {
    private int id;
    private String nombres;
    private String apellidos;
    private String ruta_foto;
    private String genero;
    private String celular;
    private String user_paciente;
    private Paciente paciente;

    public Familiar(String nombres, String apellidos, String ruta_foto, String genero, String celular, String usuario_paciente) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ruta_foto = ruta_foto;
        this.genero = genero;
        this.celular = celular;
        this.user_paciente = usuario_paciente;
    }

    public String getUser_paciente() {
        return user_paciente;
    }

    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
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

}
