package com.example.a55.lab5_tp_android_buffet.POJOS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A55 on 17/05/2017.
 */

public class Producto {

    public String tipoMenu;
    public String nombre;
    public Double precio;
    public String imagen;

    public byte[] imagenBytes;

    public static List<Producto> listaProductos;

    public Producto(){}

    public Producto(String tipoMenu, String nombre, Double precio, String imagen) {
        this.tipoMenu    = tipoMenu;
        this.nombre      = nombre;
        this.precio      = precio;
        this.imagen      = imagen;
        this.imagenBytes = null;
    }
}
