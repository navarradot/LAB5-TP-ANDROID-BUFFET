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

/**
 * Created by A55 on 18/05/2017.
 */

public class AdapterMenu extends RecyclerView.Adapter<ViewHolderMenu> implements Handler.Callback {

    private MenuView menuView;
    public Handler handler;
    private int onCreateCont;
    public List<ViewHolderMenu> listaHoldersMenu;

    public AdapterMenu(MenuView menuView)
    {
        this.onCreateCont = 0;
        this.menuView = menuView;

        // Lista donde guardo los holders que se crean en el onCreate, para una vez descargados (en el handlerMessage) "re-bindear el holder
        this.listaHoldersMenu = new ArrayList<>();

        // Handler para conexiones
        this.handler = new Handler(this);
    }

    @Override
    public ViewHolderMenu onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_menu, parent, false);
        ViewHolderMenu myViewHolder = new ViewHolderMenu(v);

        this.descargarImagen(onCreateCont);
        this.listaHoldersMenu.add(myViewHolder);
        this.onCreateCont++;

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMenu holder, int position) {

        holder.menuView = this.menuView;

        Producto p = Producto.listaProductos.get(position);

        holder.tvNombreProducto.setText(p.nombre);
        holder.tvPrecioProductoNumero.setText(p.precio.toString());

            // Bindea la imagen del producto (imagenBytes) con el respectivo ImageView del RecivlerView
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(p.imagenBytes, 0, p.imagenBytes.length);
                holder.ivProducto.setImageBitmap(bitmap);
            } catch (Exception e) {

                e.printStackTrace();
                Log.d("EOnBindViewH", "" + e.getMessage());
            }

        //Guarda la posicion en el holder
        holder.posicion = position;
    }

    @Override
    public int getItemCount() {
        return Producto.listaProductos.size();
    }

    @Override
    public boolean handleMessage(Message msg) {

        try {

            // descargarImagenProducto
            if (msg.arg1 == 2) {

                byte[] imagenBytes = (byte[]) msg.obj;

                // Guarda la imagenBytes en el producto de la lista para  reutilizaro en la pantalla de mi pedido
                Producto.listaProductos.get(msg.arg2).imagenBytes = imagenBytes;
                this.bindViewHolder(this.listaHoldersMenu.get(msg.arg2), msg.arg2);
            }

        } catch (Exception e) {
            Log.d("ERROR CATCH", e.getMessage());
        }
        return true;
    }

    private void descargarImagen(int posicion) {
        Producto p = Producto.listaProductos.get(posicion);

        Thread threadDescargarImagenProducto = new Thread(new ThreadConnection(handler, p.imagen, posicion,  "getImagenLista"));
        threadDescargarImagenProducto.start();
        //threadDescargarImagenProducto.join(3000);
    }
}
