package com.example.a55.lab5_tp_android_buffet.Http;

import android.util.Log;

import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A55 on 17/06/2017.
 */

public class JsonParser {

    /*
    public static List<Producto> getProductos(String str) {

        List<Producto> listaProductos = new ArrayList<Producto>();
        //Log.d("arraycito: ", str);
        try {
            String productosArray = "{ productos: ";
            productosArray = productosArray.concat(str+"}");

            JSONObject jsonObject = new JSONObject(productosArray);

            JSONArray productos = jsonObject.getJSONArray("productos");

            for (int i = 0; i < productos.length(); i++) {
                JSONObject producto = productos.getJSONObject(i);

                String tipoMenu = producto.getString("tipoMenu");
                String nombre = producto.getString("nombre");
                Double precio = producto.getDouble("precio");
                String imagen = (producto.getString("imagen"));
                //String imagen = "http";

                Producto prod = new Producto(tipoMenu, nombre, precio, imagen);
                listaProductos.add(prod);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ERRRRROOR: ", e.getMessage().toString());
        }

        return listaProductos;

    }
    */

    public static Boolean getValidacionLogin(String str) {

        Boolean rta = false;
        try {

            JSONObject jsonObject = new JSONObject(str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1));
            Log.d("A VERRR: ", jsonObject.toString());

            if(jsonObject.getInt("codigo") == 200) {
                rta = true;
            }
            if(jsonObject.getInt("codigo") == 400) {
                rta = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ERROR: ", e.getMessage().toString());
        }
        return rta;


    }

    public static Boolean getValidacionUsuarioNoExista(String str) {

        Boolean rta = false;
        try {
            String rtaArray = "{ rta: ";
            rtaArray = rtaArray.concat(str+"}");

            JSONObject jsonObject = new JSONObject(rtaArray);
            JSONArray jsonArray = jsonObject.getJSONArray("rta");

            if (jsonArray.length() == 0) {
                return true;
            }
            else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ERROR: ", e.getMessage().toString());
        }
        return rta;
    }

    /*
    public static Boolean getRegistroUsuario(String str) {

        Boolean rta = false;
        try {

            JSONObject jsonObject = new JSONObject(str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1));


            JSONArray jsonArray = jsonObject.getJSONArray("");

            if (jsonArray.length() < 0) {

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ERROR: ", e.getMessage().toString());
        }
        return rta;


    }
    */
}
