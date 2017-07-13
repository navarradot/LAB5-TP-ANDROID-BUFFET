package com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.Http.JsonParser;
import com.example.a55.lab5_tp_android_buffet.Http.ThreadConnection;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by A55 on 18/05/2017.
 */

public class AdapterMenu extends RecyclerView.Adapter<ViewHolderMenu> implements Handler.Callback {

    private MenuView menuView;
    public Handler handler;
    public ExecutorService executorService;

    public int onCreateCont;

    public AdapterMenu(MenuView menuView)
    {
        this.menuView = menuView;

        // Handler para conexiones
        this.handler = new Handler(this);

        Producto.listaProductos = new ArrayList<Producto>();
        // Trae lista de productos de la apiRest
        this.traerProductos();

        // Pool de Thread
        this.executorService = Executors.newFixedThreadPool(1);

        onCreateCont=0;
    }

    @Override
    public ViewHolderMenu onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_menu, parent, false);
        ViewHolderMenu myViewHolder = new ViewHolderMenu(v);

        Log.d("onCreatee", ""+ onCreateCont );
        onCreateCont++;

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMenu holder, int position) {

        holder.menuView = this.menuView;

        Producto p = Producto.listaProductos.get(position);

        holder.tvNombreProducto.setText(p.nombre);
        holder.tvPrecioProductoNumero.setText(p.precio.toString());

        if (p.imagenBytes == null) {

            this.descargarImagen(position);

            try {
                holder.ivProducto.setImageResource(R.mipmap.utnfra);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("EOnBindViewH", "" + e.getMessage());
            }
        }
        else {
            // Bindea la imagen del producto (imagenBytes) con el respectivo ImageView del RecivlerView
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(p.imagenBytes, 0, p.imagenBytes.length);
                holder.ivProducto.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("EOnBindViewH", "" + e.getMessage());
            }
        }

        //Guarda la posicion en el holder
        holder.posicion = position;

        Log.d("onBind", ""+ position );
    }

    @Override
    public int getItemCount() {
        return Producto.listaProductos.size();
    }

    public void traerProductos() {
        // Trae todos los productos
        try {
            Thread threadTraerTodosLosProductos = new Thread(new ThreadConnection(handler, "productos/", "getString"));
            threadTraerTodosLosProductos.start();
            //threadTraerTodosLosProductos.join();
        } catch (Exception e) {
            Log.d("ERROR CATCH", e.getMessage());
        }
    }

    public void descargarImagen(int position) {
        Producto p = Producto.listaProductos.get(position);

        Thread threadDescargarImagenProducto = new Thread(new ThreadConnection(handler, p.imagen, position,  "getImagenLista"));
        //threadDescargarImagenProducto.start();
        // Agrega Thread a Pool
        this.executorService.execute(threadDescargarImagenProducto);
    }

    @Override
    public boolean handleMessage(Message msg) {

        try {

            // traerTodosLosProductos
            if (msg.arg1 == 3) {
                Producto.listaProductos = JsonParser.getProductos((String) msg.obj);
                this.notifyDataSetChanged();
            }

            // descargarImagenProducto
            if (msg.arg1 == 2) {

                byte[] imagenBytes = (byte[]) msg.obj;

                // Guarda la imagenBytes en el producto de la lista para  reutilizaro en la pantalla de mi pedido
                Producto.listaProductos.get(msg.arg2).imagenBytes = imagenBytes;
                this.notifyItemChanged(msg.arg2);
                //this.notifyDataSetChanged();
            }
            //Error de conexión httpManager -> ThreadConnection
            if (msg.arg1 == 1000) {
                Toast.makeText(this.menuView.menuActivity, this.menuView.menuActivity.getResources().getString(R.string.problemaConexion), Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Log.d("ERROR CATCH", e.getMessage());
        }
        return true;
    }


}
