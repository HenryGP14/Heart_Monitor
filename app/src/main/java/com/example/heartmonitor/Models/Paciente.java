package com.example.heartmonitor.Models;

import java.util.List;

public class Paciente {
    private int id;
    private String nombres;
    private String apellidos;
    private String fecha_nacimiento;
    private String ruta_foto;
    private String genero;
    private String correo;
    private List<Familiar> familiares;

    public Paciente(String nombres, String apellidos, String fecha_nacimiento, String genero, String correo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.correo = correo;
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

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getRuta_foto() {
        return ruta_foto;
    }

    public String getGenero() {
        return genero;
    }

    public String getCorreo() {
        return correo;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }
}
