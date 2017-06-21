package com.example.a55.lab5_tp_android_buffet.Activities.Menu.View;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Model.MenuModel;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView.AdapterMenu;
import com.example.a55.lab5_tp_android_buffet.Http.JsonParser;
import com.example.a55.lab5_tp_android_buffet.Http.ThreadConnection;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 18/05/2017.
 */

public class MenuView implements Handler.Callback {

    /**
     * Atributos
     */

    public MenuModel menuModel;
    public Activity menuActivity;

    public RecyclerView recyclerListaProductos;
    public FloatingActionButton fabVerPedido;
    public TextView tvImporteTotalNumero;
    public TextView tvCantidadElementosNumero;

    public Handler handler;


    /**
     * Contructor
     */
    public MenuView(Activity menuActivity, MenuModel menuModel) {

        this.menuActivity = menuActivity;
        this.menuModel = menuModel;

        this.fabVerPedido = (FloatingActionButton) menuActivity.findViewById(R.id.fabVerPedido);
        this.tvImporteTotalNumero = (TextView) menuActivity.findViewById(R.id.tvImporteTotalNumero);
        this.tvImporteTotalNumero.setText(Pedido.precioTotalPedido.toString());
        this.tvCantidadElementosNumero = (TextView) menuActivity.findViewById(R.id.tvCantidadElementosNumero);
        this.tvCantidadElementosNumero.setText(Pedido.cantidadItemsPedido.toString());

        // Handler para conexiones
        this.handler = new Handler(this);

        // Trae lista de productos de la apiRest
        this.traerProductos();

    }

    public void traerProductos() {
        // Trae todos los productos
        try {
            Thread threadTraerTodosLosProductos = new Thread(new ThreadConnection(handler, "productos/", "getString"));
            threadTraerTodosLosProductos.start();
            threadTraerTodosLosProductos.join();
        } catch (Exception e) {
            Log.d("ERROR CATCH", e.getMessage());
        }
    }

    @Override
    public boolean handleMessage(Message msg) {

        try {
            // traerTodosLosProductos
            if (msg.arg1 == 3) {
                Producto.listaProductos = JsonParser.getProductos((String) msg.obj);

                // RecyclerView
                this.recyclerListaProductos = (RecyclerView)menuActivity.findViewById(R.id.RecyclerListaProductos);

                //Le decimos como presenta la informacion, puede ser grilla, columnas etc.
                LinearLayoutManager layoutManager = new LinearLayoutManager(menuActivity);

                this.recyclerListaProductos.setLayoutManager(layoutManager);

                AdapterMenu adapter = new AdapterMenu(this);
                this.recyclerListaProductos.setAdapter(adapter);
            }

        } catch(Exception e) {
            Log.d("ERROR CATCH", e.getMessage());
        }
        return true;
    }
}

