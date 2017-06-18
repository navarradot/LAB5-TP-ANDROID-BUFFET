package com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.Http.JsonParser;
import com.example.a55.lab5_tp_android_buffet.Http.ThreadConnection;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

import java.util.List;

/**
 * Created by A55 on 18/05/2017.
 */

public class AdapterMenu extends RecyclerView.Adapter<ViewHolderMenu> {

    private MenuView menuView;

    public AdapterMenu(MenuView menuView)
    {
        //this.listaProductos  = lista;
        this.menuView = menuView;
    }

    @Override
    public ViewHolderMenu onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_menu, parent, false);
        ViewHolderMenu myViewHolder = new ViewHolderMenu(v);
        //Log.d("ATENCION: ", "ENTRO AL onCreateViewHolder() " + create++);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMenu holder, int position) {
        Producto p = Producto.listaProductos.get(position);
        holder.menuView = this.menuView;

        // Descarga imagen de producto
        //Thread threadDescargarImagenProducto = new Thread(new ThreadConnection(handler, p.imagen, position, "getImagen"));
        //threadDescargarImagenProducto.start();

        //holder.ivProducto.setImageResource(this.listaProductos.get(position).imagen.getImagen());

        holder.tvNombreProducto.setText(p.nombre);
        holder.tvPrecioProductoNumero.setText(p.precio.toString());

        //Guarda la posicion en el holder
        holder.posicion = position;
        //Log.d("ATENCION: ", "ENTRO AL onBindViewHolder( )"+ bind++);
    }

    @Override
    public int getItemCount() {
        //Log.d("ATENCION: ", "ENTRO AL getItemCount() "+ count++);
        return Producto.listaProductos.size();
    }


}
