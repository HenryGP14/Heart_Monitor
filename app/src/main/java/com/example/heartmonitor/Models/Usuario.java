package com.example.heartmonitor.Models;

public class Usuario {
    private boolean response;
    private String mensaje;
    private String usuario;
    private String tipo_cuenta;
    private String clave;
    private Paciente paciente;
    private Familiar familiar;

    public Usuario(String usuario, String clave, Paciente paciente) {
        this.usuario = usuario;
        this.clave = clave;
        this.paciente = paciente;
    }

    public Usuario(String usuario, String clave, Familiar familiar) {
        this.usuario = usuario;
        this.clave = clave;
        this.familiar = familiar;
    }

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

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }
}
