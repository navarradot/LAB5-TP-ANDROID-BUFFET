package com.example.a55.lab5_tp_android_buffet.POJOS;

import android.util.Log;

import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.ReciclerView.ViewHolderMiPedido;

import java.util.List;

/**
 * Created by A55 on 18/05/2017.
 */

public class Pedido {

    public static  List<Producto> listaPedidos;
    public static  Double precioTotalPedido;
    public static  Integer cantidadItemsPedido;


    public static void agregarProductoAPedido(Producto p) {

        listaPedidos.add(p);
        actualizarDatos();
    }

    public static void quitarProductoPedido(int pIndice) {

        try {
            listaPedidos.remove(pIndice);

            actualizarDatos();
            //Log.d("indice borrado:", ""+pIndice);
        } catch (Exception e){
            Log.d("ERROR borrar:", ""+e.getMessage());
        }
    }

    public static void actualizarDatos() {

        cantidadItemsPedido = obtenerCantidadItemsPedido();
        precioTotalPedido   = obtenerPrecioTotalPedido();
    }

    public static Integer obtenerCantidadItemsPedido() {
        return listaPedidos.size();
    }

    public static Double obtenerPrecioTotalPedido() {

        Double precioTotal = 0.00;

        for (Producto p: Pedido.listaPedidos) {
            precioTotal = precioTotal + p.precio;
        }
        return precioTotal;
    }

    public static void limpiarMiPedido() {
        cantidadItemsPedido = 0;
        precioTotalPedido = 0.00;
        listaPedidos.clear();
    }
}
