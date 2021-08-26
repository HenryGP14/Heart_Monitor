package com.example.heartmonitor.Models;

import java.util.List;

public class Paciente {
    private int id;
    private String tipo_cuenta;
    private String fir_nombre;
    private String sec_nombre;
    private String fir_apellido;
    private String sec_apellido;
    private String fecha_nacimiento;
    private String ruta_foto;
    private String genero;
    private String correo;
    private List<Familiar> familiares;

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public int getId() {
        return id;
    }

    public String getFir_nombre() {
        return fir_nombre;
    }

    public String getSec_nombre() {
        return sec_nombre;
    }

    public String getFir_apellido() {
        return fir_apellido;
    }

    public String getSec_apellido() {
        return sec_apellido;
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
