package com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Model;

/**
 * Created by A55 on 14/05/2017.
 */

public class RegistroUsuarioModel {

    /**
     * Atributos
     */
    public String nombre;
    public String apellido;
    public Integer dni;
    public String email;
    public String clave1;
    public String clave2;

    /**
     * Constructor
     */
    public RegistroUsuarioModel() {

        this.nombre = "Juan";
        this.apellido = "Torrellas";
        this.dni = 20555444;
        this.email = "a@a.com";
        this.clave1 = "123";
        this.clave2 = "123";
    }

    /**
     * Getters and Setters
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave1() {
        return clave1;
    }

    public void setClave1(String clave1) {
        this.clave1 = clave1;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }
}
