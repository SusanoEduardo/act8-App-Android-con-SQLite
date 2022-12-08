package com.example.alumnos.entidades;


public class Alumnos {

    private int matricula;
    private String matriculaA;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String fecha;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getMatriculaA() {
        return matriculaA;
    }

    public void setMatriculaA(String matriculaA) {
        this.matriculaA = matriculaA;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}