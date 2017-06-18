package com.example.a55.lab5_tp_android_buffet.POJOS;

import android.util.Log;

import java.util.List;

/**
 * Created by A55 on 14/05/2017.
 */

public class Usuario {

    /*
    * Atributos
    */
    public String nombre;
    public String apellido;
    public Integer dni;
    public String mail;
    public String clave;

    public static List<Usuario> listaUsuarios;
    public static Usuario usuarioActual;

    /*
    * Constructor
    */
    public Usuario(String mail, String clave) {
        this.mail = mail;
        this.clave = clave;
    }

    public Usuario(String nombre, String apellido, Integer dni, String email, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.mail = email;
        this.clave = clave;
    }

}
