package com.example.a55.lab5_tp_android_buffet.Activities.Menu.View;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
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

public class MenuView implements Handler.Callback{

    /**
     * Atributos
     */

    public MenuModel menuModel;
    public Activity menuActivity;

    public RecyclerView recyclerListaProductos;
    public Button btnVerPedido;
    public TextView tvImporteTotalNumero;
    public TextView tvCantidadElementosNumero;

    public Handler handler;

    /**
     * Contructor
     */
    public MenuView(Activity menuActivity, MenuModel menuModel) {

        this.menuActivity = menuActivity;
        this.menuModel = menuModel;

        this.btnVerPedido = (Button)menuActivity.findViewById(R.id.btnVerPedido);
        this.tvImporteTotalNumero = (TextView) menuActivity.findViewById(R.id.tvImporteTotalNumero);
        this.tvImporteTotalNumero.setText(Pedido.precioTotalPedido.toString());
        this.tvCantidadElementosNumero = (TextView) menuActivity.findViewById(R.id.tvCantidadElementosNumero);
        this.tvCantidadElementosNumero.setText(Pedido.cantidadItemsPedido.toString());

        // Handler para conexiones
        this.handler = new Handler(this);

        // Trae todos los productos
        try {
            Thread threadTraerTodosLosProductos = new Thread(new ThreadConnection(handler, "http://192.168.0.2:3000/productos/", "getString"));
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
            if (msg.arg1 == 2) {
                Producto.listaProductos = JsonParser.getProductos((String) msg.obj);

                // RecyclerView
                this.recyclerListaProductos = (RecyclerView)menuActivity.findViewById(R.id.RecyclerListaProductos);

                //Le decimos como presenta la informacion, puede ser grilla, columnas etc.
                LinearLayoutManager layoutManager = new LinearLayoutManager(menuActivity);

                this.recyclerListaProductos.setLayoutManager(layoutManager);

                AdapterMenu adapter = new AdapterMenu(this);
                this.recyclerListaProductos.setAdapter(adapter);
            }
            // descargarImagenProducto
            /*
            if (msg.arg1 == 1) {

                byte[] imagenBytes = (byte[]) msg.obj;

                // Guarda la imagenBytes en el producto de la lista para  reutilizaro en la pantalla de mi pedido
                this.listaProductos.get(msg.arg2).imagenBytes = imagenBytes;
                try {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
                    hold.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            */
        } catch(Exception e) {
            Log.d("ERROR CATCH", e.getMessage());
        }

        return true;
    }
}
