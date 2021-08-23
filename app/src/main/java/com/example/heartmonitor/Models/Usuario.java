package com.example.heartmonitor.Models;

public class Usuario {
    private boolean response;
    private String mensaje;
    private String usuario;
    private Paciente paciente;
    private Familiar familiar;
    
    public boolean isResponse() {
        return response;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Familiar getFamiliar() {
        return familiar;
    }
}
