package com.example.a55.lab5_tp_android_buffet.POJOS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A55 on 17/05/2017.
 */

public class Producto {

    public String descripcion;
    public Double precio;

    public static List<Producto> listaProductos;

    public Producto(String descripcion, Double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }


}
