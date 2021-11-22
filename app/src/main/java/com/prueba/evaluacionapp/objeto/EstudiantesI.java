package com.prueba.evaluacionapp.objeto;

public class EstudiantesI {
    private String nombre = "";
    private String apePaterno = "";
    private String apeMaterno = "";
    private String email = "";
    private String edad= "";
    private String direccion = "";
    private String genero = "";
    private String telefono= "";
    private int id_usuario = 0;

    public EstudiantesI(String nombre, String apePaterno, String apeMaterno, String email, String edad, String direccion, String genero, String telefono, int id_usuario) {
        this.nombre = nombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.email = email;
        this.edad = edad;
        this.direccion = direccion;
        this.genero = genero;
        this.telefono = telefono;
        this.id_usuario = id_usuario;
    }

    public EstudiantesI() {
        this.nombre = nombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.email = email;
        this.edad = edad;
        this.direccion = direccion;
        this.genero = genero;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
